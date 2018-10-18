package dictionary;

import java.io.IOException;
import java.util.Scanner;

public class DictionaryCommandLine {
	DictionaryManagement manager = new DictionaryManagement();	
	
	public void run() throws IOException {
		System.out.println("1.Basic function");
		System.out.println("2.Advanced function");
		Scanner input = new Scanner(System.in);
		System.out.print("Your choise: ");
		int choise = input.nextInt();
		switch(choise) {
			case 1: dictionaryBasic();break;
			case 2: {
				dictionaryAdvanced();
				System.out.print("Do you want to export current data to file (y/n):");
				String x = input.next();
				if(x.equals("y")) manager.exportToFile();
			};break;
		}
		

	}
	
	public void showAllWord_advanced() {  // in tu - phien am - nghia
		System.out.print("\n");
		
		for( int i = 0 ; i < manager.dict.data.size(); i++)
		{
			System.out.println("No "+ (i + 1));
			System.out.println("Word: " +manager.dict.data.get(i).getWord_target() + "\t/"+ manager.dict.data.get(i).getWord_pronoun()+"/" );
			System.out.println("Explain :\n" + manager.dict.data.get(i).getWord_explain());
			System.out.print("\n");
		}
	}
	public void dictionaryBasic() {
		Scanner input = new Scanner(System.in);
		String choise;
		do {
			System.out.println("1.Enter word from commandLine");
			System.out.println("2.Show all words");
			System.out.print("Your choise: ");
			int choise_1 = input.nextInt();
			switch(choise_1) {
				case 1: manager.insertFromCommandLine();break;
				case 2: showAllWord_basic();break;
			}
			System.out.print("Do you want to contine?(1_y/2_n) : ");
			choise= input.next();
		}while ( choise.equals("1")|| choise.equals("y"));
	}
	public void showAllWord_basic() {// chi in tu va nghia
		System.out.print("\n");
		
		for( int i = 0 ; i < manager.dict.data.size(); i++)
		{
			System.out.println("No "+ (i + 1));
			System.out.println("Word: " +manager.dict.data.get(i).getWord_target() );
			System.out.println("Explain : " + manager.dict.data.get(i).getWord_explain());
			System.out.print("\n");
		}
	}
	
	public void dictionaryAdvanced() throws IOException {
		Scanner input = new Scanner(System.in);
		String choise;
		do {
			System.out.println("1. Get data from file(force in first time)");
			System.out.println("2. Show all words");
			System.out.println("3. Search word");
			System.out.println("4. Look up word");
			System.out.println("5.Other choise");
			System.out.print("Your choise: ");
			int choise_1 = input.nextInt();
			switch(choise_1) {
				case 1: manager.insertFromFile();  break;
				case 2: showAllWord_advanced();             break;
				case 3: dictionarySearch();        break;
				case 4: manager.dictionaryLookup();break;
				case 5:{
					System.out.println("1.Add word");
					System.out.println("2.Edit word");
					System.out.println("3.Delete word");
					System.out.print("Your choise: ");
					int choise_2 = input.nextInt();
					switch(choise_2) {
					case 1: manager.addData();     break;
					case 2: manager.insertData();  break;
					case 3: manager.deleteData();  break;
					}
				}break;
			}
			System.out.print("Do you want to contine?(1_y/2_n) : ");
			choise= input.next();
		}while ( choise.equals("1")|| choise.equals("y"));
	}
	
	public void dictionarySearch() {
		boolean _continue = true;
		Scanner input = new Scanner(System.in);
		do {
			System.out.print("Enter a word you want to search: ");
			String x = input.nextLine().toLowerCase();
			for ( int i = 0; i< manager.dict.data.size(); i++) {
				if ( manager.dict.data.get(i).word_target.length() >= x.length()) { // do dai target >= do dai tu can tim
					String word_split = splitString(manager.dict.data.get(i).word_target, x.length());
					if( word_split.equals(x)) {
						System.out.println(manager.dict.data.get(i).word_target);
					}
				}
			}
			System.out.print("Do you want to contine? (1_y/2_n) : ");
			char y = input.nextLine().charAt(0);
			if( y == 'n' || y == '2') _continue = false;
		}while(_continue);
		System.out.print("Do you want to translate (1_y/2_n) : ");
		String z = input.next();
		if( z.equals("1") || z.equals("y") ) manager.dictionaryLookup();
	}
	public static String splitString(String s, int n) { 
		String x = "";
		for ( int i = 0; i< n; i++) {
			x += s.charAt(i);
		}
		return x;
	}
}


