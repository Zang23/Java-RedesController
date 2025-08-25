package view;

import javax.swing.JOptionPane;

import controller.RedesController;


public class Main {

	public static void main(String[] args) {
		RedesController rc = new RedesController();
		
		int opc = 0;
		while(opc != 9) {
			opc = Integer.parseInt(JOptionPane.showInputDialog(null, "1- Conferir ip\n2-Conferir ping (www.google.com.br)\n9-Sair"));
			switch (opc){
				case 1:
					rc.ip();
					break;
				case 2:
					rc.ping();
					break;
				case 9:
					break;
				default:
					System.err.println("Opção invalida!");
					
			}
		}
	}

}
