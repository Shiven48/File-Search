package search_files;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Search_files {
	
	private static List<String> files = new ArrayList<String>();

	public static void main(String[] args) throws FileNotFoundException 
	{
		System.out.println("Enter the root path from where you want to search text : ");
		Scanner scanner = new Scanner(System.in);
		String path = scanner.next();
		
        File file = new File(path);
        
        System.out.println("Enter the string you want to search : ");
        String search_string = scanner.next();
        
        System.out.println("Searching...");
        search_directories(file, search_string);
        
        System.out.println();
        System.out.println("Search Ended");
        System.out.println();
        
        for(String f : files)
        {
        	System.out.println(f);
        }
        scanner.close();
	}
	
	// check if input file is file and return the file name having target string in it
	public static String search_str(File file,String searching_str) throws FileNotFoundException
	{
		String file_name = "";
		String data = "";
		if(isFile(file))
		{
		  boolean executable = file.setExecutable(true, false);
			if(executable)
	     	 {
			   Scanner reader = new Scanner(file);
			   while(reader.hasNextLine()) 
			   {
			       data = data.concat(reader.nextLine());
			   }
			   if(data.contains(searching_str))
			   {
				   file_name = file.getName();
			   }
			   reader.close();
		     }
	   }
		return file_name;
	 }
	
	// check if input file is a file
	public static boolean isFile(File file)
	{
		boolean filee = false;
		if(isDirectory(file) == false && file.isFile() == true)
		{
			filee = true;
		}
		return filee;
	}
	
	// check if input file is a directory 
	public static boolean isDirectory(File file)
	{
		boolean dir = false;
		if(file.isDirectory() == true)
		{
			dir = true;
		}
		return dir;
	}
	
	// (recursive) searches directories and sub directories
	public static void search_directories(File file,String target_str) throws FileNotFoundException
	{
		String search_res = "";
		
		if(isFile(file))
		{
			search_res = search_str(file,target_str);
			if(search_res.isBlank())
			{
			   return;
			}
			files.add(file.getAbsolutePath());
			return ;
		}
		else if(isDirectory(file))
		{
			// Uncomment the below line to see how the searching is taking place
//			System.out.println(file.getName());
		    File[] file_arr = file.listFiles();
		    for(File sub_file : file_arr)
		    {
			    search_directories(sub_file,target_str);
		    }
		}
		return;
	}
}

