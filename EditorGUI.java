package org.sking.notepad;

import java.awt.FileDialog;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

//import java.awt.FileDialog;
//import java.awt.Font;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.io.BufferedReader;
//import java.io.BufferedWriter;
//import java.io.FileNotFoundException;
//import java.io.FileReader;
//import java.io.FileWriter;
//import java.io.IOException;
//
//import javax.swing.JFrame;
//import javax.swing.JMenu;
//import javax.swing.JMenuBar;
//import javax.swing.JMenuItem;
//import javax.swing.JScrollPane;
//import javax.swing.JTextArea;


public class EditorGUI {
	
	JFrame frame;
	JTextArea textArea;
	JScrollPane scrollPane;
	JMenuBar menuBar;

	JMenu file, edit, format, cmd, lang;

	JMenuItem newItem, newWindowItem, openItem, saveAsItem, saveItem, exitItem;

	JMenuItem wwoItem;
	JMenu fontItem;
	JMenu fontSizeItem;

	JMenuItem arialItem, tnrItem, consolasItem;

	JMenuItem s10, s15, s18, s25, s29;

	JMenuItem cItem, cppItem, javaItem, pythonItem, jsItem, htmlItem;

	int fontSize = 15;
	String fontStyle = "Arial";

	JMenuItem cmdItem;

	public EditorGUI() {
		createFrame();
		createTextArea();
		createScrollBar();
		createMenuBar();
		createFileMenuItem();
		createFormatMenuItem();
		createLanguageItem();
		createCMDMenu();
	}

	public void createFrame() {
		frame = new JFrame("Notepad Code Editor");
		frame.setSize(700, 500);
		frame.setVisible(true);
	}

	public void createTextArea() {
		textArea = new JTextArea();
		frame.add(textArea);
	}

	public void createScrollBar() {
		scrollPane = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		frame.add(scrollPane);
	}

	public void createMenuBar() {
		menuBar = new JMenuBar();

		frame.setJMenuBar(menuBar);

		file = new JMenu("File");
		menuBar.add(file);

		edit = new JMenu("Edit");
		menuBar.add(edit);

		format = new JMenu("Format");
		menuBar.add(format);

		cmd = new JMenu("Command Prompt");
		menuBar.add(cmd);

		lang = new JMenu("Language");
		menuBar.add(lang);
	}

	public void createFileMenuItem() {
		newItem = new JMenuItem("New");
		newItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				textArea.setText("");
			}
		});
		file.add(newItem);

		newWindowItem = new JMenuItem("New Window");
		newWindowItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				EditorGUI g2 = new EditorGUI();
				g2.frame.setTitle("Untitled");
			}
		});
		file.add(newWindowItem);

		openItem = new JMenuItem("Open");
		openItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				FileDialog fd = new FileDialog(frame, "Open", FileDialog.LOAD);

				fd.setVisible(true);
				String directory = fd.getDirectory();
				String fileName = fd.getFile();

				FileReader fr;
				try {
					fr = new FileReader(directory + fileName);
					BufferedReader br = new BufferedReader(fr);

					textArea.setText("");
					frame.setTitle(fileName);

					String data = br.readLine();

					while (data != null) {
						textArea.append(data + "\n");
						System.out.println();

						data = br.readLine();
					}
					br.close();
					fr.close();

				} catch (FileNotFoundException e1) {
					System.out.println("File path issue...");
				} catch (IOException e1) {
					System.out.println("Could not read the data...");
				}
			}
		});
		file.add(openItem);

		saveAsItem = new JMenuItem("Save As");
		saveAsItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				FileDialog fd = new FileDialog(frame, "Save As", FileDialog.SAVE);

				fd.setVisible(true);

				String path = fd.getDirectory();
				String fileName = fd.getFile();
				String data = textArea.getText();

				try {
					FileWriter fw = new FileWriter(path + fileName);
					BufferedWriter bw = new BufferedWriter(fw);

					bw.write(data);

					bw.close();
					fw.close();
				} catch (IOException e1) {
					System.out.println("Issue in path...");
				}

			}
		});
		file.add(saveAsItem);

		saveItem = new JMenuItem("Save");
		file.add(saveItem);

		exitItem = new JMenuItem("Exit");
		exitItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		file.add(exitItem);
	}

	public void createFormatMenuItem() {
		wwoItem = new JMenuItem("Word Wrap: Off");
		wwoItem.addActionListener(new ActionListener() {

			boolean wrap = false;

			@Override
			public void actionPerformed(ActionEvent e) {
				if (wrap == false) {
					wwoItem.setText("Word Wrap: On");

					textArea.setLineWrap(true);
					wrap = true;
				} else {
					wwoItem.setText("Word Wrap: Off");

					textArea.setLineWrap(false);
					wrap = false;
				}
			}
		});
		format.add(wwoItem);

		fontItem = new JMenu("Font");
		format.add(fontItem);

		arialItem = new JMenuItem("Arial");
		arialItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setFont("Arial", fontSize);
			}
		});
		fontItem.add(arialItem);

		tnrItem = new JMenuItem("Times New Roman");
		tnrItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setFont("Times New Roman", fontSize);
			}
		});
		fontItem.add(tnrItem);

		consolasItem = new JMenuItem("Consolas");
		consolasItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setFont("Consolas", fontSize);
			}
		});
		fontItem.add(consolasItem);

		// FONT SIZE
		fontSizeItem = new JMenu("Font Size");
		format.add(fontSizeItem);

		s10 = new JMenuItem("10");
		fontSizeItem.add(s10);
		s10.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setFontSize(10);

			}
		});

		s15 = new JMenuItem("15");
		s15.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setFontSize(15);
			}
		});
		fontSizeItem.add(s15);

		s18 = new JMenuItem("18");
		s18.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setFontSize(18);
			}
		});
		fontSizeItem.add(s18);

		s25 = new JMenuItem("25");
		fontSizeItem.add(s25);
		s25.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setFontSize(25);
			}
		});

		s29 = new JMenuItem("29");
		s29.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setFontSize(29);
			}
		});
		fontSizeItem.add(s29);

	}

	public void setFont(String fontName, int size) {
		switch (fontName) {
		case "Times New Roman":
			textArea.setFont(new Font("Times New Roman", Font.PLAIN, fontSize));
			fontStyle = "Times New Roman";
			break;
		case "Arial":
			textArea.setFont(new Font("Arial", Font.PLAIN, fontSize));
			fontStyle = "Arial";
			break;
		case "Consolas":
			textArea.setFont(new Font("Consolas", Font.PLAIN, fontSize));
			fontStyle = "Consolas";
			break;
		default:
			break;
		}

	}

	public void setFontSize(int size) {
		fontSize = size;
		setFont(fontStyle, fontSize);

	}

	public void createCMDMenu() {
		cmdItem = new JMenuItem("Open Command Prompt");

		cmdItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					Runtime.getRuntime().exec(new String[] { "cmd", "/K", "start" });
				} catch (IOException e1) {
					System.out.println("Issue in cmd...");
				}
			}
		});
		cmd.add(cmdItem);
	}

	public void createLanguageItem() {
		cItem = new JMenuItem("C");
		cItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setLanguage("C");
			}
		});
		lang.add(cItem);

		cppItem = new JMenuItem("C++");
		cppItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setLanguage("C++");
			}
		});
		lang.add(cppItem);

		javaItem = new JMenuItem("Java");
		javaItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setLanguage("Java");
			}
		});
		lang.add(javaItem);

		pythonItem = new JMenuItem("Python");
		pythonItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setLanguage("Python");
			}
		});
		lang.add(pythonItem);
		
		
		
		jsItem = new JMenuItem("JS");
		jsItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setLanguage("JS");
			}
		});
		lang.add(jsItem);
		
		
		htmlItem = new JMenuItem("HTML");
		htmlItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setLanguage("HTML");
			}
		});
		lang.add(htmlItem);

	}

	public void setLanguage(String lang) {
		String path = "";
		switch (lang) {
		
				case "C":
					path = "E:\\Study Time\\Internship\\My Notepad\\Language_Format\\C_Format.c";
					break;
		
				case "C++":
					path = "E:\\Study Time\\Internship\\My Notepad\\Language_Format\\CPP_Format.cpp";
					break;
		
				case "Java":
					path = "E:\\Study Time\\Internship\\My Notepad\\Language_Format\\Java_Format.java";
					break;
		
				case "Python":
					path = "E:\\Study Time\\Internship\\My Notepad\\Language_Format\\Python_Format.py";
					break;
					
				case "JS":
					path = "E:\\Study Time\\Internship\\My Notepad\\Language_Format\\JS_Format.js";
					break;
					
				case "HTML":
					path = "E:\\Study Time\\Internship\\My Notepad\\Language_Format\\HTML_Format.html";
					break;
		
				default:
					break;
		}

		try {
			textArea.setText("");
			@SuppressWarnings("resource")
			BufferedReader br = new BufferedReader(new FileReader(path));
			String line = br.readLine();

			while (line != null) {
				textArea.append(line + "\n");
				System.out.println();

				line = br.readLine();
			}
		} 
		catch (IOException e) {
			System.out.println("Issue with the format");
		}

	}

}