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
		
		if(os.contains("Windows")) {
			leProc("ipconfig");
		}else if(os.contains("Linux")) {
			leProc("ifconfig");
		}
		
	}
	
	public void ping() {
		String os = os();
		
		if(os.contains("Windows 11")) {
			leProc("ping -4 -n 10 www.google.com.br");
		}else if(os.contains("Linux")) {
			leProc("ping -4 -c 10 www.google.com.br");
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
			String os = os();
	
			if(os.contains("Windows")) {
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
					
					linha = buffer.readLine();
				}
			}else if(os.contains("Linux")) {
				
				while(linha != null) {
					if(linha.contains("inet ")) {
						linha = linha.replaceAll("\\s+", " ");
						String[] vetIpLinux = linha.split(" ");
						System.out.println(vetIpLinux[2]);
					}
					
					
					
					if(linha.contains("lo: ") || linha.contains("enp")) {
						String[] vetRedeLinux = linha.split(" ");
						System.out.println(vetRedeLinux[0]);
						
					}
					
					if(linha.contains("min/avg/max")) {
						String[] vetPing = linha.split(" ");
						String[] pings = vetPing[3].split("/");
						
						System.out.println("média: " + pings[1] + "ms");
						
					}
					
					
					linha = buffer.readLine();

				}
			}
			
			
			buffer.close();
			fluxo.close();
			leitor.close();
		}catch(Exception e) {
			
		}
		
	}
	
	
	
	
	
}
