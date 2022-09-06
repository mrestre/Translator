import javax.swing.*; 
import java.util.*;
import java.io.*; 
import java.awt.event.*;
import java.awt.*;

public class Driver { 
	public static void main(String[] args) { 
		
		JFrame gui = new JFrame(); 
		Random rd = new Random(); 
		
		int random = rd.nextInt(9); 
		
		javax.swing.Timer delay = new javax.swing.Timer(10000, null); 
		
		JLabel lMessage = new JLabel("Type the translation into the field below."); 
		lMessage.setBounds(100, 10, 300, 40); 
		
		JLabel lWord = new JLabel(); 
		lWord.setBounds(40, 60, 100, 30); 
		
		JTextField tfWord = new JTextField(); 
		tfWord.setBounds(150, 60, 150, 30); 
		
		JButton okButton = new JButton("Ok"); 
		okButton.setBounds(350, 60, 50, 30);
		
		ArrayList<String> eWords = new ArrayList<String>();
		ArrayList<String> gWords = new ArrayList<String>();
		
		try { 
			BufferedReader br1 = new BufferedReader(new FileReader("english.txt")); 
			BufferedReader br2 = new BufferedReader(new FileReader("finnish.txt")); 
			String words1, words2;
		while ((words1 = br1.readLine()) != null)
		{ 
			eWords.add(words1);
			} 
		br1.close(); 
		while ((words2 = br2.readLine()) != null)
		{ 
			gWords.add(words2);
			} 
		br2.close(); 
		} catch (IOException e) { 
			e.printStackTrace(); 
			}
		lWord.setText(eWords.get(random));
		ActionListener changeWord = new ActionListener()
		{
			@Override public void actionPerformed(ActionEvent event) 
			{ 
				int i = rd.nextInt(9); 
				int k = rd.nextInt(9);
				if (k % 2 == 0) lWord.setText(eWords.get(i));
				else 
					lWord.setText(gWords.get(i)); 
				lMessage.setText("Type the translation into the field below"); 
				tfWord.setText(null);
				tfWord.setEnabled(true);
				} 
			};
			delay.addActionListener(changeWord);
			okButton.addActionListener(new ActionListener() 
			{
				public void actionPerformed(ActionEvent e)
				{
					tfWord.setEnabled(false);
					String displayedWord = lWord.getText();
					String userWord = tfWord.getText(); 
					if (eWords.indexOf(displayedWord) != -1) 
					{ 
						int index = eWords.indexOf(displayedWord);
						String correctWord = gWords.get(index);
						if (userWord.equalsIgnoreCase(correctWord)) 
						{ 
							lMessage.setText("Correct!");
							} else {
								lMessage.setText("Incorrect! Answer:" + correctWord); 
								tfWord.setText("something wrong");
								}
						} else { 
							int index = gWords.indexOf(displayedWord); 
							String correctWord = eWords.get(index);
							if (userWord.equalsIgnoreCase(correctWord))
							{
								lMessage.setText("Correct!");
								} else {
									lMessage.setText("Incorrect! Answer:" + correctWord); 
									tfWord.setText("something wrong");
									} 
							} 
					delay.setRepeats(false);
					delay.start();
					} 
				});
			tfWord.addKeyListener(new KeyAdapter()
			{ 
				public void keyPressed(KeyEvent event) 
				{ 
					int x = event.getKeyCode();
					if (event.getKeyCode() == 9)
					{ 
						tfWord.setEnabled(false);
						String displayedWord = lWord.getText();
						String userWord = tfWord.getText();
						if (eWords.indexOf(displayedWord) != -1) 
						{ 
							int index = eWords.indexOf(displayedWord);
							String correctWord = gWords.get(index); 
							if (userWord.equalsIgnoreCase(correctWord))
							{
								lMessage.setText("Correct!");
								} else { 
									lMessage.setText("Incorrect! Answer:" + correctWord); 
									tfWord.setText("something wrong");
									} 
							
	//							Switch Word:
	//								case A: 
	//									System.out.println("Correct Answer: "+ correctWord);
	//									return;
	//								case B: 
	//									if(int i = 0, i < 0, i++){
	//									System.out.println("Incorrect Answer: "+ IncorrectWord);
	//									}
	//									return;
	//								default: 
	//									System.out.println("The answer entered is not a word");
	//									return;
							
										
							} else { 
								int index = gWords.indexOf(displayedWord);
								String correctWord = eWords.get(index);
								if (userWord.equalsIgnoreCase(correctWord))
								{ 
									lMessage.setText("Correct!");
									} else { 
										lMessage.setText("Incorrect! Answer:" + correctWord); 
										tfWord.setText("something wrong");
										} 
								} 
						delay.setRepeats(false); 
						delay.start(); 
						} 
					} 
				});
			gui.add(lMessage);
			gui.add(lWord);
			gui.add(tfWord); 
			gui.add(okButton); 
			gui.setSize(450, 150); 
			gui.setLayout(null); 
			gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			gui.setVisible(true); 
			} 
	}
