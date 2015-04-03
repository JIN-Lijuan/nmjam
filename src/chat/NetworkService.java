package chat;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Logger;

class NetworkService implements Runnable {
	private ServerSocket serverSocket;
	private final ExecutorService pool;
	private int port;
	Dispatcher dispatcher = new Dispatcher();

	// Server logger
	private final static Logger LOGGER = 
			Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);


	public NetworkService(int port, int poolSize){
		this.port = port;
		try {
			this.serverSocket = new ServerSocket(port);
		} catch (IOException e) {
			LOGGER.severe("Cannot open server socket on port " + port);

		}

		pool = Executors.newFixedThreadPool(poolSize);
		LOGGER.info("Network Service Initialised.");
	}

	public int getPort() {
		return port;
	}

	public void run() { 
		// run the service
		try {
			for (;;) {
				// Client socket
				Socket cl = serverSocket.accept();
				LOGGER.info("Ceonnection accepted from " + 
						cl.getInetAddress() +
						"on port" +
						cl.getPort());

				dispatcher.addClient(cl);
				pool.execute(new EchoThread(cl, dispatcher));
				LOGGER.finest("Thread added to ThreadPool");

			}
		} catch (IOException ex) {
			pool.shutdown();

		}
	}

	public synchronized void  shutDown(){
		LOGGER.info("Server is shutting down");
		LOGGER.finest("Shutting down ThreadPool");
		pool.shutdown();
		LOGGER.finest("Shutting down ServerSocket");
		try {
			serverSocket.close();
		} catch (IOException e) {
			LOGGER.severe("Cannot close ServerSocket" + e.getMessage());

		}

	}
}

