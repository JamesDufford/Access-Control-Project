package project_2;
import java.util.*;



import java.io.*;


public class Methods {
	
	//Variables for methods
	static File Friends = new File ("friends.txt");
	static File Audit_Log = new File ("audit.txt");
	static int iF = 0;
	static int ownerperm = 0;
	static String Viewer = "";
	
	//list linked list nodes
	static L_node first ;
	static L_node last ;
	
	//picture linked list nodes
	static P_node P_first ;
	static P_node P_last ;
	
	//variables for use in later methods
	public static int i = 0;
	public static int z = 0;
	
	
	//node class for lists
	public static class  L_node{
		
		public  String Listname;
		public  File Members;
		public  L_node next ;
		public  int Total_Members = 0;
		
	}
	// node class for pictures
		public static class  P_node{
		
		public 	String Owner;
		public  String Picname;
		public  File Picture;
		public  File Members;
		public  P_node next ;
		public  L_node List = new L_node();
		public  String rw1;
		public  String rw2;
		public  String rw3;
		
		
	}
	
	public static void friendadd (String friendname) {
		
		int p = 0;
		int d = 0;
		Scanner test;
	//checking to see if this is the first time a friend has been added
			try {
				if ( iF == 0) {
					
					FileWriter w = new FileWriter(Friends);
					
					
					w.append(friendname + "\n");
					
					w.close();
					
					iF++;
					
					System.out.println(friendname + " has been added and is the profile owner.");
					
					FileWriter a = new FileWriter(Audit_Log);
					
					
					a.append(friendname + " has been added and is the profile owner." + "\n");
					
					a.close();
					
				}else {
					
					try {
						test = new Scanner(Friends);
						
						while (test.hasNext()) {
						
								if(test.next().equals(friendname)== true) {
								d++;
								break;
								
							}
							}
						}catch(FileNotFoundException e) {
						
						System.out.println("IO error at scanner in friend add.");
					}
					//checking to see if friend has already been added
					if (d == 0) {
						
					// checking to see if the viewer has owner permissions
					if(ownerperm == 0)	{
					
					System.out.println(Viewer + " is not the profile owner and cannot add friends" );
					
					
					FileWriter a = new FileWriter(Audit_Log, true);
					
					
					a.append(Viewer + " is not the profile owner and cannot add friends" + "\n");
					
					a.close();
					
				}else {
					//adding friend
				FileWriter w = new FileWriter(Friends,true);
			
			
				w.append(friendname + "\n");
				
				w.close();
				
				System.out.println(friendname + " has been added to the list");
				
				
				FileWriter a = new FileWriter(Audit_Log, true);
				
				
				a.append(friendname + " has been added to the list" + "\n");
				
				a.close();
				
				}
					}
					else {
						try {
							System.out.println(friendname + " is already on the list.");
							
							FileWriter a = new FileWriter(Audit_Log);
							
							
							a.append(friendname + " is already on the list." + "\n");
							
							a.close();
							}catch(IOException e) {
								System.out.println("IO Exception throw on File Writer in friendadd");
							}
					}
				}
			} catch (IOException e) {
				System.out.println("IO Exception throw on File Writer in friendadd");
			}
			
		}
	
	public static void viewby (String friendname) {
			
  		Scanner test;
  		int i = 1;
  		int p = 0;
		try {
			test = new Scanner(Friends);
			
			while (test.hasNext()) {
				//setting viewer looking through the friends list to find them
				if(i== 1) {
					if(test.next().equals(friendname)== true) {
						ownerperm = 1;
						Viewer = friendname;
						p = 1;
						break;
					}else {
						i++;
					}
				}else {
					if(test.next().equals(friendname)== true) {
					Viewer = friendname;
					p = 1;
					break;
					
				}
				}
			}
			
		}catch(FileNotFoundException e) {
			
			System.out.println("IO error at scanner in viewby.");
		}
		//checking to see if friend was on the list
		
		if(p == 0) {
			try {
				System.out.println(friendname + " is not on the list and therefore they cannot view the profile"  );
			FileWriter a = new FileWriter(Audit_Log, true);
			
			
			a.append(friendname + " is not on the list and therefore they cannot view the profile" + "\n");
			
			a.close();
			}catch( IOException e) {
				
				System.out.println("IO error at scanner in viewby.");
			} 
			//checking to see if the owner is running the viewby
		}else if( ownerperm == 1) {
			try {
			System.out.println(Viewer + " is viewing the profile and is the owner of the profile" );
			FileWriter a = new FileWriter(Audit_Log, true);
			
			
			a.append(Viewer + " is viewing the profile and is the owner of the profile" + "\n");
			
			a.close();
			
		}catch( IOException e) {
			
			System.out.println("IO error at scanner in viewby.");
		}
		}else {
			try {
				System.out.println(Viewer + " is viewing the profile" );
				FileWriter a = new FileWriter(Audit_Log, true);
				
				
				a.append(Viewer + " is viewing the profile" + "\n");
				
				a.close();
				
			}catch( IOException e) {
				
				System.out.println("IO error at scanner in viewby.");
			}
		}
			
	}
	
	
	public static void logout () {
		
		try {
		System.out.println(Viewer + " is logging out");
		
		
		FileWriter a = new FileWriter(Audit_Log, true);
		
		
		a.append(Viewer + " is logging out" + "\n");
		
		a.close();
		
		} catch(IOException e) {
		System.out.println("IO Exception throw on File Writer in logout");
	}
		//reseting owner and viewer status
		ownerperm = 0;
		Viewer = "";
		
	}
	
	
	public static void listadd (String listname) {
	//nil exception variable
	String x = "nil";
	try {
	//checking owner permissions		
		if(ownerperm == 0)	{
			
			System.out.println(Viewer + " is not the profile owner and cannot add lists" );
			
			
			FileWriter a = new FileWriter(Audit_Log, true);
			
			
			a.append(Viewer + " is not the profile owner and cannot add lists" + "\n");
			
			a.close();
			//checking nil
		}else if(listname.equals(x)) {
			
			System.out.println(Viewer + " cannot create a list named nil" );
			
			
			FileWriter a = new FileWriter(Audit_Log, true);
			
			
			a.append(Viewer + " cannot create a list named nil" + "\n");
			
			a.close();
			//checking to see if this is the first list added
		}else {
			if(i == 0) {
				//creating new list
				first = new L_node();
				first.Listname = listname;
				String Mem = first.Listname + ".txt";
				first.Members = new File (Mem);
				last = first;
				i++;
				System.out.println(Viewer + " has created list: " + last.Listname + " and it's members are stored in " + last.Members );
				
				
				FileWriter a = new FileWriter(Audit_Log, true);
				
				
				a.append(Viewer + " has created list: " + last.Listname + " and it's members are stored in " + last.Members + "\n");
				
				a.close();
				
	}else {
		L_node c = first;
		int p = 0;
		//checking to see if the list is already made
	try {
			while(c != null){
				if(c.Listname.equals(listname) == true) {
					
					System.out.println(Viewer + " has tried to create list " + listname + ", but it already has been created. ");
					
					FileWriter a = new FileWriter(Audit_Log, true);
					
					a.append(Viewer + " has tried to add " + listname + ", but it already has been created. " + "\n");
					
					a.close();
					p++;
					break;
				}else {
					c = c.next;
				}
			}
			//creating new list
			if(p == 0) {
		last.next= new L_node();
		
		last.next.Listname = listname;
		
		last.next.Members = new File (listname + ".txt");
		
		last = last.next;
			
			
	
	
			System.out.println(Viewer + " has created list: " + last.Listname + " and it's members are stored in " + last.Members );
			
			
			FileWriter a = new FileWriter(Audit_Log, true);
			
			
			a.append(Viewer + " has created list: " + last.Listname + " and it's members are stored in " + last.Members + "\n");
			
			a.close();
		}
		
		
		}catch (IOException e) {
			System.out.println("IO Exception throw on File Writer in listadd");
		}
		
	
		}
			}
		}catch (IOException e) {
			System.out.println("IO Exception throw on File Writer in listadd");
		}
	
	}
	
	
	public static void friendlist (String friendname, String listname) {
		
		int p = 0;
		int d = 0;
		Scanner test;
		try {
			test = new Scanner(Friends);
			
			while (test.hasNext()) {
			
					if(test.next().equals(friendname)== true) {
					d++;
					break;
					
				}
				}
			}catch(FileNotFoundException e) {
			
			System.out.println("IO error at scanner in viewby.");
		}
		//checking to see if the owner is viewing the profile
		if(ownerperm == 1) {
		//checking to see if friend is on the list
		if(d == 1) {
		try {
			//checked owner permission twice not sure why, but I didn't wanna mess with a working program so I left it in
			if(ownerperm == 0)	{
				
				System.out.println(Viewer + " is not the profile owner and cannot add freinds to lists." );
				
				
				FileWriter a = new FileWriter(Audit_Log, true);
				
				
				a.append(Viewer + " is not the profile owner and cannot add firends to lists." + "\n");
				
				a.close();
				
			}else {
				//looking to make sure list is real
				L_node x = first;
				if(x == null) {
					System.out.println(Viewer + " has tried to add to a list that is not real");
					
					
					FileWriter a = new FileWriter(Audit_Log, true);
					
					
					a.append(Viewer + " has tried to add to a list that is not real"  + "\n");
					p++;
				 a.close();
			}else {
				//adding friend to list if list is found
					while(x != null){
						if(x.Listname.equals(listname) == true) {
							FileWriter w;
							if(x.Total_Members == 0) {
								w = new FileWriter(x.Members);
								x.Total_Members++;
							}else {
								 w = new FileWriter(x.Members, true);
							}
							
							w.append(friendname + "\n");
							
							w.close();
							
							System.out.println(Viewer + " has added " + friendname+ " to list: " + x.Listname);
							
							FileWriter a = new FileWriter(Audit_Log, true);
							
							a.append(Viewer + " has added " + friendname+ " to list: " + x.Listname + "\n");
							
							a.close();
							p++;
							break;
						}else {
							x = x.next;
						}
					}
				}
				//error for if list is not found
				if(p ==0) {
					System.out.println(Viewer + " has tried to add to a list that is not real");
				
					
					FileWriter a = new FileWriter(Audit_Log, true);
					
					
					a.append(Viewer + " has tried to add to a list that is not real"  + "\n");
					
					a.close();
				}
				
			
			
			}
			}catch (IOException e) {
				System.out.println("IO Exception throw on File Writer in listadd");
			}
		}else {
			//error for if friend is not on list
			try {
				System.out.println(friendname + " is not on the list and therefore they cannot be added to a list"  );
			FileWriter a = new FileWriter(Audit_Log, true);
			
			
			a.append(friendname + " is not on the friends list and therefore they cannot be added to a list" + "\n");
			
			a.close();
			}catch( IOException e) {
				
				System.out.println("IO error at scanner in friendlist.");
			}
		}
		
		}else {
			try {
				// error for if viewer is not profile owner
			System.out.println(Viewer + " is not the profile owner and cannot add friends to lists" );
			
			
			FileWriter a = new FileWriter(Audit_Log, true);
			
			
			a.append(Viewer + " is not the profile owner and cannot add friends to lists" + "\n");
			
			a.close();
			
			}catch( IOException e) {
				
				System.out.println("IO error at scanner in friendlist.");
		}
		}
		
	}
	
	
	public static void postpicture (String picturename) {
		//checking to make sure someone is viewing the profile
		if(Viewer.equals("") == true) {
			try {
			System.out.println("No one is viewing the profile and therefore no one can post pictures");
			
			FileWriter a = new FileWriter(Audit_Log, true);
			
			
			a.append("No one is viewing the profile and therefore no one can post pictures" + "\n");
			
			a.close();
			}catch (IOException e) {
				
				System.out.println("IOEception in post picture");
		}
		//checking to see if a picture has already been posted
	}else if(z == 0) {
		//creating first picture
		try {
		P_first = new P_node();
		P_first.Picname = picturename;
		P_first.Owner = Viewer;
		
		String Mem = P_first.Picname;
		P_first.Picture = new File (Mem);
		P_last = P_first;
		z++;
		P_first.rw1 = "rw";
		P_first.rw2 = "--";
		P_first.rw3 = "--";
		P_first.List = new L_node();
		P_first.List.Listname = "nil";
		
		FileWriter p = new FileWriter(P_first.Picture);
		
		p.append(Mem + "\n");
		
		p.close();
		
		System.out.println(Viewer + " has posted picture: " + P_first.Picname + " and is the owner of the picture" );
		
		FileWriter a = new FileWriter(Audit_Log, true);

		a.append(Viewer + " has posted picture: " + P_first.Picname + " and is the owner of the picture" + "\n");
		
		a.close();
		
		}catch (IOException e) {
		System.out.println("IO Exception throw on File Writer in listadd");
	}
		
	}else {
		//checking to see if the picture was already posted
		P_node c = P_first;
		int p = 0;
		
	try {
			while(c != null){
				if(c.Picname.equals(picturename) == true) {
					//error for if picture is already posted
					
					System.out.println(Viewer + " has tried to create a picture " + picturename + ", but it already has been created. ");
					
					FileWriter a = new FileWriter(Audit_Log, true);
					
					a.append(Viewer + " has tried to create a picture " + picturename + ", but it already has been created. " + "\n");
					
					a.close();
					p++;
					break;
				}else {
					c = c.next;
				}
			}
			
			if(p == 0) {
				//making new picture
		P_last.next= new P_node();
		
		P_last.next.Picname = picturename;
		P_last.next.Owner = Viewer;
		
		String Mem = P_last.next.Picname;
		P_last.next.Picture = new File (Mem);
		P_last.next.rw1 = "rw";
		P_last.next.rw2 = "--";
		P_last.next.rw3 = "--";
		P_last.next.List = new L_node();
		P_last.next.List.Listname = "nil";
		FileWriter d = new FileWriter(P_last.next.Picture);
		
		d.append(Mem + "\n");
		
		d.close();
		
		 P_last = P_last.next;
		 
		 	System.out.println(Viewer + " has posted picture: " + P_last.Picname + " and is the owner of the picture" );
			
			
			FileWriter a = new FileWriter(Audit_Log, true);
			
			
			a.append(Viewer + " has posted picture: " + P_last.Picname + " and is the owner of the picture" + "\n");
			
			a.close();
		}
		
		
		}catch (IOException e) {
			System.out.println("IO Exception throw on File Writer in listadd");
		}
		
	}
	}
		
	
	public static void chlst (String picturename, String listname) {
		//checking to see if someone is viewing the profile
		if(Viewer.equals("") == true) {
			try {
			System.out.println("No one is viewing the profile and therefore no one can post pictures");
			
			FileWriter a = new FileWriter(Audit_Log, true);
			
			
			a.append("No one is viewing the profile and therefore no one can post pictures" + "\n");
			
			a.close();
			}catch (IOException e) {
				
				System.out.println("IOEception in post picture");
		}
	}else {
		//checking to see if the picture is already posted
		P_node x = P_first;
		int z =0;
		while (x != null) {
			if(x.Picname.equals(picturename)== true) {
				z ++;
				break;
			}else {
				x = x.next;
			}
		}
		
		if(z == 0) {
			//error for if picture is not posted
			try {
				System.out.println(Viewer + " has tried to add a list to a picture that does not exsist");
				
				FileWriter a = new FileWriter(Audit_Log, true);
				
				
				a.append(Viewer + " has tried to add a list to a picture that does not exsist" + "\n");
				
				a.close();
				}catch (IOException e) {
					
					System.out.println("IOEception in chlst");
			}
		}else {
			//check to see if is real
			int L = 0;
			L_node l = first;
			while (l != null) {
				if(l.Listname.equals(listname)== true) {
					L ++;
					break;
				}else {
					l = l.next;
				}
			}
			//error for if list is not real
			if(L == 0 && listname.equals("nil") == false) {
				try {
					System.out.println(Viewer + " has tried to add a picture to a list that does not exsist");
					
					FileWriter a = new FileWriter(Audit_Log, true);
					
					
					a.append(Viewer + " has tried to add a picture to a list that does not exsist" + "\n");
					
					a.close();
					}catch (IOException e) {
						
						System.out.println("IOEception in chlst");
				}
			//check to see if owner is viewing
			}else if(ownerperm == 1) {
				//check for nil
				if(listname.equals("nil")== false) {
				x.List = l;
				
				try {
					System.out.println(Viewer + " has added the picture: " + x.Picname + " to the list: " + x.List.Listname );
					
					FileWriter a = new FileWriter(Audit_Log, true);
					
					
					a.append(Viewer + " has added the picture: " + x.Picname + " to the list: " + x.List.Listname);
					
					a.close();
					}catch (IOException e) {
						
						System.out.println("IOEception in chlst");
				}
				}else {
					//nil is real so list is removed
					x.List = new L_node();
					x.List.Listname = listname;
					x.rw1 = "rw";
					x.rw2 = "--";
					x.rw3 = "--";
					
					try {
						System.out.println(Viewer + " has removed the picture: " + x.Picname + " from its former list and is now labled as " + x.List.Listname );
						
						FileWriter a = new FileWriter(Audit_Log, true);
						
						
						a.append(Viewer + " has removed the picture: " + x.Picname + " from its former list and is now labled as " + x.List.Listname + "\n");
						
						a.close();
						
						}catch (IOException e) {
							
							System.out.println("IOEception in chlst");
					}
					
				}
				
				//check so see if the view is the picture owner
			}else if(x.Owner.equals(Viewer) == true) {
				//check for nil
				if(listname.equals("nil")== false) {
				Scanner test;
		  		int g = 0;
		  	//check to see if owner is on the list 
				try {
					test = new Scanner(l.Members);
					
					while (test.hasNext()) {
						
							if(test.next().equals(Viewer)== true) {
								g++;
							}else {
							
							}
						
						}
					}catch(FileNotFoundException e) {
					
					System.out.println("IO error at scanner in chlist.");
				}
				
				if(g == 0) {
					//error for owner no being on the list
					try {
						System.out.println(Viewer + " is not a member of the list" );
						
						FileWriter a = new FileWriter(Audit_Log, true);
						
						
						a.append(Viewer + " is not a member of the list " + "\n" );
						
						a.close();
						
						}catch (IOException e) {
							
							System.out.println("IOEception in chlst");
					}
					
				}else {
					
					x.List = l;
					
					try {
						
						System.out.println(Viewer + " has added the picture: " + x.Picname + " to the list: " + x.List.Listname );
						
						FileWriter a = new FileWriter(Audit_Log, true);
						
						
						a.append(Viewer + " has added the picture: " + x.Picname + " to the list: " + x.List.Listname + "\n");
						
						a.close();
						}catch (IOException e) {
							
							System.out.println("IOEception in chlst");
					}
					
				}	
					
				}else {
					//nil is true so list is deleted and replaced with nil
					x.List = new L_node();
					x.List.Listname = listname;
					x.rw1 = "rw";
					x.rw2 = "--";
					x.rw3 = "--";
					try {
						System.out.println(Viewer + " has removed the picture: " + x.Picname + " from its former list and is now labled as " + x.List.Listname );
						
						FileWriter a = new FileWriter(Audit_Log,true);
						
						
						a.append(Viewer + " has removed the picture: " + x.Picname + " from its former list and is now labled as " + x.List.Listname + "\n");
						
						a.close();
						
						}catch (IOException e) {
							
							System.out.println("IOEception in chlst");
					}
				}
				
			}else {
				try {
					//error for inability to call this method
					System.out.println(Viewer + " is neither the owner of the profile or the owner of the picture" );
					
					FileWriter a = new FileWriter(Audit_Log, true);
					
					
					a.append(Viewer + " is neither the owner of the profile or the owner of the picture" + "\n");
					
					a.close();
					
					}catch (IOException e) {
						
						System.out.println("IOEception in chlst");
				}
				
			}
			}
			
		}
		
		
		
	}
		
	
	public static void chmod (String picturename, String rw1, String  rw2, String rw3) {
	//check to see if someone is viewing the profile	
		if(Viewer.equals("") == true) {
			
			try {
			System.out.println("No one is viewing the profile and therefore no one can change the permissions of a pictrue");
			
			FileWriter a = new FileWriter(Audit_Log, true);
			
			
			a.append("No one is viewing the profile and therefore no one can post pictures" + "\n");
			
			a.close();
			}catch (IOException e) {
				
				System.out.println("IOEception in chmod");
		}
	}else {
		//check to see if picture is real
		P_node x = P_first;
		int z =0;
		while (x != null) {
			if(x.Picname.equals(picturename)== true) {
				z ++;
				break;
			}else {
				x = x.next;
			}
		}
		
		if(z == 0) {
			//error for picture not being posted
			try {
				System.out.println(Viewer + " has tried to change permissions on a picture that has not been posted yet");
				
				FileWriter a = new FileWriter(Audit_Log, true);
				
				
				a.append(Viewer + Viewer + " has tried to change permissions on a picture that has not been posted yet" + "\n");
				
				a.close();
				}catch (IOException e) {
					
					System.out.println("IOEception in chlst");
			}
			
			
			
	}else {
		//check to see if viewer is owner of the profile
		if(ownerperm == 1) {
			x.rw1 = rw1;
			x.rw2 = rw2;
			x.rw3 = rw3;
			
			try {
				System.out.println(Viewer + " has changed the permissions of picture " + x.Picname + " to " + x.rw1 + " " + x.rw2 + " " + x.rw3 );
				
				FileWriter a = new FileWriter(Audit_Log, true);
				
				
				a.append(Viewer + " has changed the permissions of picture " + x.Picname + " to " + x.rw1 + " " + x.rw2 + " " + x.rw3  + "\n");
				
				a.close();
				}catch (IOException e) {
					
					System.out.println("IOEception in chmod");
			}
			
			
			
		}else {
			//check to see if viewer is owner of the picture
			if(x.Owner.equals(Viewer) == true) {
				x.rw1 = rw1;
				x.rw2 = rw2;
				x.rw3 = rw3;
				
				try {
					System.out.println(Viewer + " has changed the permissions of picture " + x.Picname + " to " + x.rw1 + " " + x.rw2 + " " + x.rw3 );
					
					FileWriter a = new FileWriter(Audit_Log, true);
					
					
					a.append(Viewer + " has changed the permissions of picture " + x.Picname + " to " + x.rw1 + " " + x.rw2 + " " + x.rw3  + "\n");
					
					a.close();
					}catch (IOException e) {
						
						System.out.println("IOEception in chmod");
				}
			}else {
				//error if viewer does not have ability to use the method
				try {
					System.out.println(Viewer + " tried to change the permissions to a picture they do not own and they are not the profil owner.");
					
					FileWriter a = new FileWriter(Audit_Log, true);
					
					
					a.append(Viewer + " tried to change the permissions to a picture they do not own and they are not the profil owner."  + "\n");
					
					a.close();
					}catch (IOException e) {
						
						System.out.println("IOEception in chmod");
				}
				
			}
			
		}
		
	}
	
}
		
	}
	
	
	public static void chown (String picturename, String friendname) {
		//checking if owner is viewing
		if(ownerperm == 0)	{
			try {
			
			System.out.println(Viewer + " is not the profile owner and cannot change owners" );
			
			
			FileWriter a = new FileWriter(Audit_Log, true);
			
			
			a.append(Viewer + " is not the profile owner and cannot change owners" + "\n");
			
			a.close();
			
			}catch(IOException e) {
				
				System.out.println("IOEception in chmod");
			}
		}else {
			//check to see if friend is on the list
			int d = 0;
			Scanner test;
			try {
				test = new Scanner(Friends);
				
				while (test.hasNext()) {
				
						if(test.next().equals(friendname)== true) {
						d++;
						break;
						
					}
					}
				}catch(FileNotFoundException e) {
				
				System.out.println("IO error at scanner in chown.");
			}
			//error for if friend is not on the list
			if(d ==0) {
				try {
					System.out.println(friendname + " is not on the friends list and therefore they cannot be made the owner of a picture"  );
				FileWriter a = new FileWriter(Audit_Log, true);
				
				
				a.append(friendname + " is not on the friends list and therefore they cannot be made the owner of a picture" + "\n");
				
				a.close();
				}catch( IOException e) {
					
					System.out.println("IO error at scanner in chown.");
				}
				
			}else {
				//check to make sure picture is posted
				P_node x = P_first;
				int z =0;
				while (x != null) {
					if(x.Picname.equals(picturename)== true) {
						z ++;
						break;
					}else {
						x = x.next;
					}
				}
				//error for if picture is not posted
				if(z == 0) {
					try {
						System.out.println(Viewer + " has tried to change the owner of a picture that is not posted");
						
						FileWriter a = new FileWriter(Audit_Log, true);
						
						
						a.append(Viewer + " has tried to change the owner of a picture that is not posted" + "\n");
						
						a.close();
						}catch (IOException e) {
							
							System.out.println("IOEception in chown");
					}
				}else {
					try {
						//change of ownership of the picture
					x.Owner = friendname;
					
					System.out.println(Viewer + " has changed the owner of picture: " + x.Picname + " to " + x.Owner);
					
					FileWriter a = new FileWriter(Audit_Log, true);
					
					
					a.append(Viewer + " has changed the owner of picture: " + x.Picname + " to " + x.Owner + "\n");
					
					a.close();
					}catch (IOException e) {
						
						System.out.println("IOEception in chown");
				}
					
				}
				
			}
			
			
		}
		
		
		
		
		
	}
	
	
	public static void readcomments (String picturename) {
	//check to make sure profile is being viewed
	if(Viewer.equals("") == true) {
			
			try {
			System.out.println("No one is viewing the profile and therefore no one can write comments");
			
			FileWriter a = new FileWriter(Audit_Log, true);
			
			
			a.append("No one is viewing the profile and therefore no one can write comments" + "\n");
			
			a.close();
			}catch (IOException e) {
				
				System.out.println("IOEception in read comments");
		}
	}else {
		//check to make sure picture is posted
		P_node x = P_first;
		int z =0;
		while (x != null) {
			if(x.Picname.equals(picturename)== true) {
				z ++;
				break;
			}else {
				x = x.next;
			}
		}
		//error for picture not being posted
		if(z == 0) {
			
			try {
				System.out.println(Viewer + " has tried to readcomments from a picture that has not been posted.");
				
				FileWriter a = new FileWriter(Audit_Log, true);
				
				
				a.append(Viewer + " has tried to readcomments from a picture that has not been posted." + "\n");
				
				a.close();
				}catch (IOException e) {
					
					System.out.println("IOEception in read comments");
			}
			
		}else {
			
			Scanner test;
			int b = 0;
			//check to see if picture owner is the viewer
			if( x.Owner.equals(Viewer) == true ) {
					
					
			//checking permissions to see if viewing is allowed		
				if(x.rw1.equals("rw") == true) {
					
					try {
						test = new Scanner(x.Picture);
						
						while (test.hasNextLine()) {
						
								if(b == 0) {
									
									try {
										
										System.out.println(Viewer + " is the owner of the post and is acessing comments");
										String f = test.nextLine();
										System.out.println(f);
										FileWriter a = new FileWriter(Audit_Log, true);
										
										
										a.append(Viewer + " is the owner of the post and is acessing comments" + "\n");
										a.append(f + "\n");
										a.close();
										
										}catch (IOException e) {
											
											System.out.println("IOEception in read comments");
									}
								}else {
									try {
									FileWriter a = new FileWriter(Audit_Log, true);
									
									String f = test.nextLine();
									System.out.println(f);
									a.append(f + "\n");
									a.close();
									
									}catch (IOException e) {
										
										System.out.println("IOEception in read comments");
								}
								}
								
							}
							}catch(FileNotFoundException e) {
						
						System.out.println("IO error at scanner in read comments.");
					}
					
				}else if(x.rw1.equals("r-") == true) {
					try {
						test = new Scanner(x.Picture);
						
						while (test.hasNextLine()) {
						
								if(b == 0) {
									
									try {
										
										System.out.println(Viewer + " is the owner of the post and is acessing comments");
										String f = test.nextLine();
										System.out.println(f);
										FileWriter a = new FileWriter(Audit_Log, true);
										
										b++;
										a.append(Viewer + " is the owner of the post and is acessing comments" + "\n");
										a.append(f + "\n");
										a.close();
										
										}catch (IOException e) {
											
											System.out.println("IOEception in read comments");
									}
								}else {
									try {
									FileWriter a = new FileWriter(Audit_Log, true);
									
									String f = test.nextLine();
									System.out.println(f);
									a.append(f + "\n");
									a.close();
									
									}catch (IOException e) {
										
										System.out.println("IOEception in read comments");
								}
								}
								
							}
							}catch(FileNotFoundException e) {
						
						System.out.println("IO error at scanner in read comments.");
					}
					
				}else {
					//viewing is not allowed so error is thrown
					try {
						FileWriter a = new FileWriter(Audit_Log, true);
						System.out.println(Viewer + " does not have authority to read these comments");

						a.append(Viewer + " does not have authority to read these comments" + "\n");
						a.close();
						
						}catch (IOException e) {
							
							System.out.println("IOEception in read comments");
					}
					
				}
				
			}else {
				//check to see if view is on a attached lists members file
				int n = 0; 
				//check to see if the picture has a list
				if(x.List.Listname.equals("nil") == true){
					//check to see if viewer is on the friends list
					int c = 0;
					try {
					test = new Scanner(Friends);
					while (test.hasNextLine()) {
						if(Viewer.equals(test.nextLine()) == true) {
							c++;
						}
				}
					}catch(FileNotFoundException e) {
						
						System.out.println("IO error at scanner in read comments.");
					}
					
				//error for if viewer is not on the friends list
					if(c == 0) {
						try {
							FileWriter a = new FileWriter(Audit_Log, true);
							System.out.println(Viewer + " does not have authority to read these comments");

							a.append(Viewer + " does not have authority to read these comments" + "\n");
							a.close();
							
							}catch (IOException e) {
								
								System.out.println("IOEception in read comments");
						}
					}else {
						//checking permissions to see if reading is allowed
					int d =0;
						if(x.rw3.equals("rw") == true) {
							
							try {
								test = new Scanner(x.Picture);
								
								while (test.hasNextLine()) {
								
										if(d == 0) {
											
											try {
												
												System.out.println(Viewer + " is acessing comments");
												String f = test.nextLine();
												System.out.println(f);
												FileWriter a = new FileWriter(Audit_Log, true);
												d++;
												
												a.append(Viewer + " is acessing comments" + "\n");
												a.append(f + "\n");
												a.close();
												
												}catch (IOException e) {
													
													System.out.println("IOEception in read comments");
											}
										}else {
											try {
											FileWriter a = new FileWriter(Audit_Log, true);
											
											String f = test.nextLine();
											System.out.println(f);
											a.append(f + "\n");
											a.close();
											
											}catch (IOException e) {
												
												System.out.println("IOEception in read comments");
										}
										}
										
									}
									}catch(FileNotFoundException e) {
								
								System.out.println("IO error at scanner in read comments.");
							}
							
						}else if(x.rw3.equals("r-") == true) {
							
							try {
								test = new Scanner(x.Picture);
								
								while (test.hasNextLine()) {
								
										if(b == 0) {
											
											try {
												
												System.out.println(Viewer + " acessing comments");
												String f = test.nextLine();
												System.out.println(f);
												FileWriter a = new FileWriter(Audit_Log, true);
												b++;
												
												a.append(Viewer + " acessing comments" + "\n");
												a.append(f + "\n");
												a.close();
												
												}catch (IOException e) {
													
													System.out.println("IOEception in read comments");
											}
										}else {
											try {
											FileWriter a = new FileWriter(Audit_Log, true);
											
											String f = test.nextLine();
											System.out.println(f);
											a.append(f + "\n");
											a.close();
											
											}catch (IOException e) {
												
												System.out.println("IOEception in read comments");
										}
										}
										
									}
									}catch(FileNotFoundException e) {
								
								System.out.println("IO error at scanner in read comments.");
							}
							
						}else {
							//view is not allowed so error is thrown
							try {
								FileWriter a = new FileWriter(Audit_Log, true);
								System.out.println(Viewer + " does not have authority to read these comments");

								a.append(Viewer + " does not have authority to read these comments" + "\n");
								a.close();
								
								}catch (IOException e) {
									
									System.out.println("IOEception in read comments");
							}
							
						}
					}
					
				}else {
					//picture has list
					//check to see if viewer is on list
					try {
					test = new Scanner(x.List.Members);
					
					while (test.hasNextLine()) {
						if(Viewer.equals(test.nextLine()) == true) {
							n++;
						}
					}
					}catch(FileNotFoundException e) {
						
						System.out.println("IO error at scanner in read comments.");
					}
					//viewer is not on list
					if(n==0) {
						//check to see if view is on friends list
						int c = 0;
						try {
						test = new Scanner(Friends);
						while (test.hasNextLine()) {
							if(Viewer.equals(test.nextLine()) == true) {
								c++;
							}
					}
						}catch(FileNotFoundException e) {
							
							System.out.println("IO error at scanner in read comments.");
						}
						
						//error viewer is not on friends list
						if(c == 0) {
							try {
								FileWriter a = new FileWriter(Audit_Log, true);
								System.out.println(Viewer + " does not have authority to read these comments");

								a.append(Viewer + " does not have authority to read these comments" + "\n");
								a.close();
								
								}catch (IOException e) {
									
									System.out.println("IOEception in read comments");
							}
						}else {
						//checking permissions	
						int d =0;
							if(x.rw3.equals("rw") == true) {
								
								try {
									test = new Scanner(x.Picture);
									
									while (test.hasNextLine()) {
									
											if(d == 0) {
												
												try {
													
													System.out.println(Viewer + " is acessing comments");
													String f = test.nextLine();
													System.out.println(f);
													FileWriter a = new FileWriter(Audit_Log, true);
													d++;
													
													a.append(Viewer + " is acessing comments" + "\n");
													a.append(f + "\n");
													a.close();
													
													}catch (IOException e) {
														
														System.out.println("IOEception in read comments");
												}
											}else {
												try {
												FileWriter a = new FileWriter(Audit_Log, true);
												

												String f = test.nextLine();
												System.out.println(f);
												a.append(f + "\n");
												a.close();
												
												}catch (IOException e) {
													
													System.out.println("IOEception in read comments");
											}
											}
											
										}
										}catch(FileNotFoundException e) {
									
									System.out.println("IO error at scanner in read comments.");
								}
								
							}else if(x.rw3.equals("r-") == true) {
								
								try {
									test = new Scanner(x.Picture);
									
									while (test.hasNextLine()) {
									
											if(b == 0) {
												
												try {
													
													System.out.println(Viewer + " acessing comments");
													String f = test.nextLine();
													System.out.println(f);
													FileWriter a = new FileWriter(Audit_Log, true);
													b++;
													
													a.append(Viewer + " acessing comments" + "\n");
													a.append(f + "\n");
													a.close();
													
													}catch (IOException e) {
														
														System.out.println("IOEception in read comments");
												}
											}else {
												try {
												FileWriter a = new FileWriter(Audit_Log, true);
												

												String f = test.nextLine();
												System.out.println(f);
												a.append(f + "\n");
												a.close();
												
												}catch (IOException e) {
													
													System.out.println("IOEception in read comments");
											}
											}
											
										}
										}catch(FileNotFoundException e) {
									
									System.out.println("IO error at scanner in read comments.");
								}
								
							}else {
								//error viewer does not have permission to read comments
								try {
									FileWriter a = new FileWriter(Audit_Log, true);
									System.out.println(Viewer + " does not have authority to read these comments");

									a.append(Viewer + " does not have authority to read these comments" + "\n");
									a.close();
									
									}catch (IOException e) {
										
										System.out.println("IOEception in read comments");
								}
								
							}
						}
				}else {
					//viewer is no list attached to picture
					//checking permissions 
					int d =0;
						if(x.rw2.equals("rw") == true) {
							
							try {
								test = new Scanner(x.Picture);
								
								while (test.hasNextLine()) {
								
										if(d == 0) {
											
											try {
												
												System.out.println(Viewer + " is acessing comments");
												String f = test.nextLine();
												System.out.println(f);
												FileWriter a = new FileWriter(Audit_Log, true);
												d++;
												
												a.append(Viewer + " is acessing comments" + "\n");
												a.append(f + "\n");
												a.close();
												
												}catch (IOException e) {
													
													System.out.println("IOEception in read comments");
											}
										}else {
											try {
											FileWriter a = new FileWriter(Audit_Log, true);
											

											String f = test.nextLine();
											System.out.println(f);
											a.append(f + "\n");
											a.close();
											
											}catch (IOException e) {
												
												System.out.println("IOEception in read comments");
										}
										}
										
									}
									}catch(FileNotFoundException e) {
								
								System.out.println("IO error at scanner in read comments.");
							}
							
						}else if(x.rw2.equals("r-") == true) {
							
							try {
								test = new Scanner(x.Picture);
								
								while (test.hasNextLine()) {
								
										if(b == 0) {
											
											try {
												
												System.out.println(Viewer + " is acessing comments");
												String f = test.nextLine();
												System.out.println(f);
												FileWriter a = new FileWriter(Audit_Log, true);
												
												
												a.append(Viewer + " is acessing comments" + "\n");
												a.append(f + "\n");
												a.close();
												
												}catch (IOException e) {
													
													System.out.println("IOEception in read comments");
											}
										}else {
											try {
											FileWriter a = new FileWriter(Audit_Log, true);
											

											String f = test.nextLine();
											System.out.println(f);
											a.append(f + "\n");
											a.close();
											
											}catch (IOException e) {
												
												System.out.println("IOEception in read comments");
										}
										}
										
									}
									}catch(FileNotFoundException e) {
								
								System.out.println("IO error at scanner in read comments.");
							}
							
						}else {
							//error viewer does not have permission to look a picture comments
							try {
								FileWriter a = new FileWriter(Audit_Log, true);
								System.out.println(Viewer + " does not have authority to read these comments");

								a.append(Viewer + " does not have authority to read these comments" + "\n");
								a.close();
								
								}catch (IOException e) {
									
									System.out.println("IOEception in read comments");
							}
							
						}
				}
				
			}
			
		}
		
	}
	}
		
	}
	
	
	public static void writecomments (String picturename, String text) {
	//check to make sure someone is viewing the profile
	if(Viewer.equals("") == true) {
			
			try {
			System.out.println("No one is viewing the profile and therefore no one can write comments");
			
			FileWriter a = new FileWriter(Audit_Log, true);
			
			
			a.append("No one is viewing the profile and therefore no one can write comments" + "\n");
			
			a.close();
			}catch (IOException e) {
				
				System.out.println("IOEception in write comments");
		}
	}else {
		//check to make sure picture is posted
		P_node x = P_first;
		int z =0;
		while (x != null) {
			if(x.Picname.equals(picturename)== true) {
				z ++;
				break;
			}else {
				x = x.next;
			}
		}
		
		if(z == 0) {
			//error picture is not posted
			try {
				System.out.println(Viewer + " has tried to write comments from a picture that has not been posted.");
				
				FileWriter a = new FileWriter(Audit_Log, true);
				
				
				a.append(Viewer + " has tried to write comments from a picture that has not been posted." + "\n");
				
				a.close();
				}catch (IOException e) {
					
					System.out.println("IOEception in write comments");
			}
		}else {
			 //check to see if viewer is the owner of the picture  
			if(x.Owner.equals(Viewer) == true) {
			//permissions check
				if(x.rw1.equals("rw") == true) {
					
					try {
						FileWriter w = new FileWriter(x.Picture, true);	
						

						w.append(text + "\n");
						w.close();		
						
						FileWriter a = new FileWriter(Audit_Log, true);
						
						System.out.println(Viewer + " is commenting: " + text + " to picture: " + x.Picname);
								
						a.append(Viewer + " is commenting: " + text + " to picture: " + x.Picname + "\n");
						a.close();
								
							}catch(IOException e) {
						
						System.out.println("IO error at scanner in read comments.");
					}
					
				}else if(x.rw1.equals("-w") == true) {
					
					try {
						FileWriter w = new FileWriter(x.Picture, true);	
						

						w.append(text + "\n");
						w.close();		
						
						FileWriter a = new FileWriter(Audit_Log, true);
						
						System.out.println(Viewer + " is commenting: " + text + " to picture: " + x.Picname);
								
						a.append(Viewer + " is commenting: " + text + " to picture: " + x.Picname + "\n");
						a.close();
								
							}catch(IOException e) {
						
						System.out.println("IO error at scanner in read comments.");
					}
				}else {
					//error viewer does not have the authority to write comments
					try {
						FileWriter a = new FileWriter(Audit_Log, true);
						System.out.println(Viewer + " does not have authority to write these comments");

						a.append(Viewer + " does not have authority to write these comments" + "\n");
						a.close();
						
						}catch (IOException e) {
							
							System.out.println("IOEception in write comments");
					}
				}
				
			}else {
				Scanner test;
			//check to see if picture is attached to a list
				if(x.List.Listname.equals("nil") == true) {
					//picture is not attached to a list
					
					//check to see if viewer is on the friends list
					int c = 0;
					try {
					test = new Scanner(Friends);
					while (test.hasNextLine()) {
						if(Viewer.equals(test.nextLine()) == true) {
							c++;
						}
				}
					}catch(FileNotFoundException e) {
						
						System.out.println("IO error at scanner in write comments.");
					}
					
				//error viewer is not on the friends list
					if(c == 0) {
						try {
							FileWriter a = new FileWriter(Audit_Log, true);
							System.out.println(Viewer + " does not have authority to write these comments");

							a.append(Viewer + " does not have authority to write these comments" + "\n");
							a.close();
							
							}catch (IOException e) {
								
								System.out.println("IOEception in write comments");
						}
					}else {
						//checking permissions
						if(x.rw3.equals("rw") == true) {
							
							try {
											
								FileWriter w = new FileWriter(x.Picture, true);	
								

								w.append(text + "\n");
								w.close();		
								
								FileWriter a = new FileWriter(Audit_Log, true);
								
								System.out.println(Viewer + " is commenting: " + text + " to picture: " + x.Picname);
										
								a.append(Viewer + " is commenting: " + text + " to picture: " + x.Picname + "\n");
								a.close();
										}
									catch(IOException e) {
								
								System.out.println("IO error at scanner in write comments.");
							}
							
						}else if(x.rw3.equals("-w") == true) {
							
							try {
								try {
									
									FileWriter w = new FileWriter(x.Picture, true);	
									

									w.append(text + "\n");
									w.close();		
									
									FileWriter a = new FileWriter(Audit_Log, true);
									
									System.out.println(Viewer + " is commenting: " + text + " to picture: " + x.Picname);
											
									a.append(Viewer + " is commenting: " + text + " to picture: " + x.Picname + "\n");
									a.close();
											}
										catch(FileNotFoundException e) {
									
									System.out.println("IO error at scanner in read comments.");
								}
									}catch(IOException e) {
								
								System.out.println("IO error at scanner in read comments.");
							}
							
						}else {
							//error viewer does not have ability to write comments
							try {
								FileWriter a = new FileWriter(Audit_Log, true);
								System.out.println(Viewer + " does not have authority to write these comments");

								a.append(Viewer + " does not have authority to write these comments" + "\n");
								a.close();
								
								}catch (IOException e) {
									
									System.out.println("IOEception in write comments");
							}
							
						}
					}
				}else {
					//picture has an attached list
					//check to see if viewer is on the attached lists member file
					int n =0;
					try {
						test = new Scanner(x.List.Members);
						
						while (test.hasNextLine()) {
							if(Viewer.equals(test.nextLine()) == true) {
								n++;
							}
						}
						}catch(FileNotFoundException e) {
							
							System.out.println("IO error at scanner in read comments.");
						}
					//viewer is not on the attached list member file
					if(n == 0) {
						//check to see if viewer is on the friends list
						int c = 0;
						try {
						test = new Scanner(Friends);
						while (test.hasNextLine()) {
							if(Viewer.equals(test.nextLine()) == true) {
								c++;
							}
					}
						}catch(FileNotFoundException e) {
							
							System.out.println("IO error at scanner in read comments.");
						}
							//error viewer is not on friends list
						if(c == 0) {
							try {
								FileWriter a = new FileWriter(Audit_Log, true);
								System.out.println(Viewer + " does not have authority to write these comments");

								a.append(Viewer + " does not have authority to write these comments" + "\n");
								a.close();
								
								}catch (IOException e) {
									
									System.out.println("IOEception in write comments");
							}
						}else {
							//checking permissions
							if(x.rw3.equals("rw") == true) {
								
								try {
												
									FileWriter w = new FileWriter(x.Picture, true);	
									

									w.append(text + "\n");
									w.close();		
									
									FileWriter a = new FileWriter(Audit_Log, true);
									
									System.out.println(Viewer + " is commenting: " + text + " to picture: " + x.Picname);
											
									a.append(Viewer + " is commenting: " + text + " to picture: " + x.Picname + "\n");
									a.close();
											}
										catch(IOException e) {
									
									System.out.println("IO error at scanner in write comments.");
								}
								
							}else if(x.rw3.equals("-w") == true) {
								
								try {
									try {
										
										FileWriter w = new FileWriter(x.Picture, true);	
										

										w.append(text + "\n");
										w.close();		
										
										FileWriter a = new FileWriter(Audit_Log, true);
										
										System.out.println(Viewer + " is commenting: " + text + " to picture: " + x.Picname);
												
										a.append(Viewer + " is commenting: " + text + " to picture: " + x.Picname + "\n");
										a.close();
												}
											catch(FileNotFoundException e) {
										
										System.out.println("IO error at scanner in read comments.");
									}
										}catch(IOException e) {
									
									System.out.println("IO error at scanner in read comments.");
								}
						}else {
							//error viewer does not have ability to write comments
							try {
								FileWriter a = new FileWriter(Audit_Log, true);
								System.out.println(Viewer + " does not have authority to write these comments");

								a.append(Viewer + " does not have authority to write these comments" + "\n");
								a.close();
								
								}catch (IOException e) {
									
									System.out.println("IOEception in write comments");
							}
						}
						}
					}else {
						//viewer is on an attached list
						//checking permissions
							if(x.rw2.equals("rw") == true) {
							
							try {
											
								FileWriter w = new FileWriter(x.Picture, true);	
								

								w.append(text + "\n");
								w.close();		
								
								FileWriter a = new FileWriter(Audit_Log, true);
								
								System.out.println(Viewer + " is commenting: " + text + " to picture: " + x.Picname);
										
								a.append(Viewer + " is commenting: " + text + " to picture: " + x.Picname + "\n");
								a.close();
										}
									catch(IOException e) {
								
								System.out.println("IO error at scanner in write comments.");
							}
							
						}else if(x.rw2.equals("-w") == true) {
							
							try {
								try {
									
									FileWriter w = new FileWriter(x.Picture, true);	
									

									w.append(text + "\n");
									w.close();		
									
									FileWriter a = new FileWriter(Audit_Log, true);
									
									System.out.println(Viewer + " is commenting: " + text + " to picture: " + x.Picname);
											
									a.append(Viewer + " is commenting: " + text + " to picture: " + x.Picname + "\n");
									a.close();
											}
										catch(FileNotFoundException e) {
									
									System.out.println("IO error at scanner in read comments.");
								}
									}catch(IOException e) {
								
								System.out.println("IO error at scanner in read comments.");
							}
						}else {
							//error viewer does not have authority to write comments
							try {
								FileWriter a = new FileWriter(Audit_Log, true);
								System.out.println(Viewer + " does not have authority to write these comments");

								a.append(Viewer + " does not have authority to write these comments" + "\n");
								a.close();
								
								}catch (IOException e) {
									
									System.out.println("IOEception in write comments");
							}
						}
					}
					
					
				}
			}
			
			
		}
		
		
		
		}
	}
	
	
	public static void end () {
		
		 File Pics = new File ("Pictures.txt");
		 File Lists = new File ("Lists.txt");
		 int p = 0;
		 int l = 0;
		 Scanner test;
		 FileWriter w;
		 
		 P_node x = P_first;
		 //getting data for pictures.txt file
		 while(x != null) {
			 
			 if(p == 0) {
				 try {
					
				 w = new FileWriter(Pics);
				 w.append("\n");
				 w.append("The picture name is: " + x.Picname + "\n");
				 w.append("The owner is: " + x.Owner + "\n");
				 w.append("Its attached list is named: " + x.List.Listname+ "\n"); 
				 w.append("The picture permissions are: " + x.rw1 + " "+ x.rw2 + " " + x.rw3 + "\n");
				 p++;
				 w.close();
				 }catch(IOException e) {
					 System.out.println("IO exception in end");
				 }
			 }else {
				 try {
					
				 w = new FileWriter(Pics,true);
				 w.append("\n");
				 w.append("The picture name is: " + x.Picname + "\n");
				 w.append("The owner is: " + x.Owner + "\n");
				 w.append("Its attached list is named: " + x.List.Listname+ "\n"); 
				 w.append("The picture permissions are: " + x.rw1 + " "+ x.rw2 + " " + x.rw3 + "\n");
				 w.close();
			 }catch(IOException e) {
				 System.out.println("IO exception in end");
			 }
			 }
			 x = x.next;
			 
		 }
		 //getting data for list.txt file
		 L_node z = first;
		 
	 while(z != null) {
			 
			 if(l == 0) {
				 try {
					 w = new FileWriter(Lists);
					 w.append("\n");
					 w.append("The list is named " + z.Listname +"\n");
					 l++;
					 
					 try{
						 if(z.Members!=null) {
							 
							 w.append("The members of this list are: " + "\n");
						 test = new Scanner(z.Members);
						 
						 while(test.hasNextLine()) {
							String f = test.nextLine();
							 
							 w.append(f +"\n");
						 }
						 
						 
						 }else {
							 w.append("This list has no members " + "\n");
						 }
						 w.close();
					 }catch(FileNotFoundException e) {
						 System.out.println("IO exception in end");
					 
					 
					 }
					 
					 }catch(IOException e) {
						 System.out.println("IO exception in end");
					 } 
				 
				 
			 }else {
				 try {
					 w = new FileWriter(Lists,true);
					 w.append("\n");
					 w.append("The list is named " + z.Listname +"\n");
					 
					 try{
						 if(z.Members!=null) {
							 
							 w.append("The members of this list are: " + "\n");
						 test = new Scanner(z.Members);
						 
						 while(test.hasNextLine()) {
							String f = test.nextLine();
							 
							 w.append(f +"\n");
						 }
						 
						 
						 }else {
							 w.append("This list has no members " + "\n");
						 }
						 w.close(); 
					 }catch(FileNotFoundException e) {
						 System.out.println("IO exception in end");
					 
					 
					 }
				 }catch(IOException e) {
					 System.out.println("IO exception in end");
				 }
			 }
			 z = z.next;
		
	 }
	 
	 try {
		 //printing message and appending to audit log
			System.out.println("End has been called and clean up has been done");
			
			FileWriter a = new FileWriter(Audit_Log, true);
			
			
			a.append("End has been called and clean up has been done" + "\n");
			
			a.close();
			}catch (IOException e) {
				
				System.out.println("IOEception in write comments");
		}
	 
	 
	}
	

}


