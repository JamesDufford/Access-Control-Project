package project_2;

import java.util.*;
import java.io.*;




public class Access {
	//Main
	public static void main(String args[]) {
		System.out.println("The instructions file is named: " + args[0]);
		File Instructions = new File (args[0]);

 //Scanner to read instructions
  	int i = 1;
  		Scanner test;
  		
		try {
			
			test = new Scanner(Instructions);
			
			while(test.hasNext()) {
			String f = test.next() ;
				if(f.equals("friendadd") == true) {
					String t = test.next();
					Methods.friendadd(t);
					
				} else if(f.equals("viewby") == true) {
					String t = test.next();
					Methods.viewby(t);
					
				} else if(f.equals("logout") == true) {
					Methods.logout();
					
				} else if(f.equals("listadd") == true) {
					String t = test.next();
					Methods.listadd(t);
					
				} else if(f.equals("friendlist") == true) {
					String t = test.next();
					String b = test.next();
					Methods.friendlist(t , b);
					
				} else if(f.equals("postpicture") == true) {
					String t = test.next();
					Methods.postpicture(t);
					
				} else if(f.equals("chlst") == true) {
					String t = test.next();
					String b = test.next();
					Methods.chlst(t , b);
					
				} else if(f.equals("chmod") == true) {
					String t = test.next();
					String b = test.next();
					String c = test.next();
					String d = test.next();
					Methods.chmod(t , b, c,d);
					
				} else if(f.equals("chown") == true) {
					String t = test.next();
					String b = test.next();
					Methods.chown(t , b);
					
				} else if(f.equals("readcomments") == true) {
					String t = test.next();
					Methods.readcomments(t);
					            
				} else if(f.equals("writecomments") == true) {
					
					String t = test.next();
					String b = test.next();
					Methods.writecomments(t , b);
				
				} else if(f.equals("end") == true) {
					Methods.end();
				} else {		
					
}
				
				
				
			}
		} catch (FileNotFoundException e) {
			
			System.out.println("IO error at scanner.");
		}
		

}
}
