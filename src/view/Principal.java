package view;

import controller.ThreadController;
import java.util.concurrent.Semaphore;

public class Principal {

	public static void main(String[] args) {
		int permissoes = 1;
		Semaphore semaforo = new Semaphore(permissoes);
		
		for(int id = 1; id <= 3; id++) {
			Thread tc = new ThreadController(id, semaforo);
			tc.start();
		}
	}

}
