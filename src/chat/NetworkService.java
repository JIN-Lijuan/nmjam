package chat;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Cette classe contien la socket du serveur et le Pool de
 * Threads.
 * @author Giuseppe FEDERICO
 *
 */
class NetworkService implements Runnable {
	private ServerSocket serverSocket;
	private final ExecutorService pool;
	private int port;
	private boolean isStopped;
	Dispatcher dispatcher = new Dispatcher();

	// Server logger
	private final static Logger LOGGER = 
			Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

	//TODO: empecher le bloque du port
	public NetworkService(int port, int poolSize){
		
		
		this.isStopped = false;
		this.port = port;
		//TODO: Déplacer l'init hors du constructeur
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
			while(! isStopped()){ 
				LOGGER.info("Waiting for connections.");
				Socket cl = serverSocket.accept();
				LOGGER.info("Ceonnection accepted from " + 
						cl.getInetAddress() +
						" on port " +
						serverSocket.getLocalPort());

				pool.execute(new EchoThread(cl, dispatcher));
				LOGGER.finest("Thread added to ThreadPool");

			}
		} catch (IOException e) {
			pool.shutdown();
			try {
				serverSocket.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			LOGGER.severe("Cannot accept connection" + e.getMessage());
		}
	}

	private synchronized boolean isStopped() {
		return this.isStopped;

	}

	public synchronized void stop(){
		this.isStopped = true;
		LOGGER.info("Stopping Server.");

		try {
			serverSocket.close();
		} catch (IOException e) {
			LOGGER.severe("Cannot close ServerSocket" + e.getMessage());

		}

	}

}

