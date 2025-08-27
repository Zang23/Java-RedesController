package controller;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class RedesController {

	
	public RedesController() {
		super();
	}
	
	private String os() {
		String os = System.getProperty("os.name");
		return os;
	}
	
	public void ip(){
		String os = os();
		
		if(os.contains("Windows 11")) {
			leProc("ipconfig");
		}else if(os.contains("Linux")) {
			leProc("ifconfig");
		}
		
	}
	
	public void ping() {
		String os = os();
		
		if(os.contains("Windows 11")) {
			leProc("ping -4 -n 10 www.google.com.br");
		}
	}
	
	
	public void chamaProc(String proc) {
		
		String[] vetProc = proc.split(" ");
		try {
			Runtime.getRuntime().exec(vetProc);
		}catch(Exception e){
			System.err.println(e.getMessage());
		}
	}
	
	public void leProc(String proc) {
		String[] vetProc = proc.split(" ");
		try {
			Process p = Runtime.getRuntime().exec(vetProc);
			InputStream fluxo = p.getInputStream();
			InputStreamReader leitor = new InputStreamReader(fluxo);
			BufferedReader buffer = new BufferedReader(leitor);
			String linha = buffer.readLine();
			while(linha != null) {
				
				if(linha.contains("Adaptador Ethernet")) {
					System.out.println(linha);
				}
				
				if(linha.contains("IPv4")) {
					String[] linhaIp = linha.split(" ");
					System.out.println(linhaIp[linhaIp.length-1]);
				}
				
				
				if(linha.contains(" = ") && linha.contains("ms")) {
					String[] linhaPing = linha.split(" ");
					StringBuffer bufferLinha = new StringBuffer();
					bufferLinha.append("Média: ").append(linhaPing[linhaPing.length-1]);
					System.out.println(bufferLinha);
				}
				
				if(linha.contains("lo: ") || linha.contains("enp")) {
					String[] vetRedeLinux = linha.split(" ");
					System.out.println(vetRedeLinux[0]);
				}
				
				if(linha.contains("inet ")) {
					String[] vetIpLinux = linha.split(" ");
					System.out.println(vetIpLinux[1]);
				}
				
				linha = buffer.readLine();
			}
			buffer.close();
			fluxo.close();
			leitor.close();
		}catch(Exception e) {
			
		}
		
	}
	
	
	
	
	
}
