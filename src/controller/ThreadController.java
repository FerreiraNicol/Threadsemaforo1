package controller;

import java.util.concurrent.Semaphore;

public class ThreadController extends Thread{
	private int tid, tempo;
	private Semaphore semaforo;
	
	public ThreadController(int tid, Semaphore semaforo) {
		this.tid = tid;
		this.semaforo = semaforo;
	}
	
	@Override
	public void run() {
		int opera = tid % 3;
		if(opera == 2) {
			for(int i = 0; i < 2; i++) {
				operacalc(opera);
				try {
					semaforo.acquire();
					acessobd(opera);
				}catch(InterruptedException e1) {
					e1.printStackTrace();
				}finally {
				semaforo.release();
				System.out.println("A thread #" +tid+ " saiu no banco de dados.");
				}
			}
		}else {
			for(int i = 0; i < 3; i++) {
				operacalc(opera);
				try {
					semaforo.acquire();
					acessobd(opera);
				}catch(InterruptedException e1) {
					e1.printStackTrace();
				}finally {
				semaforo.release();
				System.out.println("A thread #" +tid+ " saiu do banco de dados.");
				}
			}
		}
		
		
	}
	
	
	public void operacalc(int opera) {
		switch(opera) {
		case 2:
			tempo = (int)(Math.random() * 801) + 200;
			System.out.println("A thread #"+tid+" está fazendo um cálculo de "+tempo/1000.0+" s.");
			try {
				sleep(tempo);
			}catch(Exception e) {
				e.printStackTrace();
			}
			break;
		case 1:
			tempo = (int)(Math.random() * 1001) + 500;
			System.out.println("A thread #"+tid+" está fazendo um cálculo de "+tempo/1000.0+" s.");
			try {
				sleep(tempo);
			}catch(Exception e) {
				e.printStackTrace();
			}
			break;
		case 0:
			tempo = (int)(Math.random() * 1001) + 1000;
			System.out.println("A thread #"+tid+" está fazendo um cálculo de "+tempo/1000.0+" s.");
			try {
				sleep(tempo);
			}catch(Exception e) {
				e.printStackTrace();
			}
			break;
		}
	}
	
	public void acessobd(int opera) {
		System.out.println("A thread #"+tid+" entrou no banco de dados.");
		if(opera == 2) {
			tempo = 1000;
		}else {
			tempo = 1500;
		}
		System.out.println("A thread #"+tid+" está transacionando no banco de dados por "+tempo/1000.0+" s.");
		try {
			sleep(tempo);
		}catch(Exception e2) {
			e2.printStackTrace();
		}
	}
}
