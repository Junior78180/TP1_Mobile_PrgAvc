package org.example;

public class Main {

	public static void mainTP2(String[] args) {
		// TODO Auto-generated method stub
		Affichage TA = new Affichage("AAA");
		Affichage TB = new Affichage("BB");
		Affichage TC = new Affichage("CCCC");
		Affichage TD = new Affichage("DDD");

		TA.start();
		TC.start();
		TD.start();
		TB.start();

	}

	public static void main(String[] args) {
		BoiteALettre boiteALettre = new BoiteALettre();
		Producer producer = new Producer(boiteALettre);
		Consumer consumer = new Consumer(boiteALettre);

		producer.start();
		consumer.start();
	}
}
