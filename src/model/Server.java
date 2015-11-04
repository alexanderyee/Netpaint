package model;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Vector;

// Alex Yee
public class Server {
	public static final int SERVER_PORT = 9001;

	private static ServerSocket sock;
	private static List<ObjectOutputStream> clients = Collections.synchronizedList(new ArrayList<>());
	public static Vector<PaintObject> allPaintObjects = new Vector<PaintObject>();

	public static void main(String[] args) throws IOException {
		sock = new ServerSocket(SERVER_PORT);
		System.out.println("Server started on port " + SERVER_PORT);

		while (true) {
			Socket s = sock.accept();
			ObjectInputStream is = new ObjectInputStream(s.getInputStream());
			ObjectOutputStream os = new ObjectOutputStream(s.getOutputStream());
			synchronized (clients) {
				clients.add(os);
			}
			os.reset();
			os.writeObject(allPaintObjects);
			Thread ch = new ClientHandler(is, clients);
			ch.start();
			System.out.println("Accepted a new connection from " + s.getInetAddress());
		}
	}
}

class ClientHandler extends Thread {

	ObjectInputStream input;
	private List<ObjectOutputStream> clients;

	public ClientHandler(ObjectInputStream input, List<ObjectOutputStream> clients) {
		this.input = input;
		this.clients = clients;

	}

	@Override
	public void run() {
		while (true) {
			try {
				Server.allPaintObjects = (Vector<PaintObject>) input.readObject();
				writePaintObjects();
			} catch (ClassNotFoundException | IOException e1) {
				try {
					input.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		}
		
	}

	private void writePaintObjects() {
		synchronized (clients) {
			Set<ObjectOutputStream> closed = new HashSet<>();
			for (ObjectOutputStream client : clients) {
				try {
					client.reset();
					client.writeObject(Server.allPaintObjects);
				} catch (IOException e) {
					
					closed.add(client);
					
				}
				clients.removeAll(closed);
			}
		}
	}
}
