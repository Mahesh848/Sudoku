import java.util.*;
import java.util.Random;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.Date;
class Name extends JFrame implements ActionListener{
	JTextField tf;
	Name(){
		JLabel l = new JLabel("Enter Your Name: ");
		l.setBounds(100,100,150,50);
		add(l);
		tf = new JTextField();
		tf.setBounds(250,110,150,30);
		add(tf);
		JButton b = new JButton("Next");
		b.setBounds(200,170,80,30);
		b.addActionListener(this);
		add(b);
		setBounds(250,200,500,500);
		setLayout(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
	public void actionPerformed(ActionEvent e){
		String name;
		name = tf.getText();
		try{
			dispose();
		}catch(Exception e1){}
		setVisible(false);
		new Typ(name);
	}
	public static void main(String[] args) {
		new Name();
	}
}
class Typ extends JFrame implements ActionListener{
	JRadioButton rb1,rb2,rb3;
	String name;
	Typ(String name){
		this.name = name;
		rb1 = new JRadioButton("Hard");
		rb2 = new JRadioButton("Medium");
		rb3 = new JRadioButton("Easy");
		ButtonGroup gb = new ButtonGroup();
		gb.add(rb1);
		gb.add(rb2);
		gb.add(rb3);
		rb1.setBounds(150,100,100,50);
		rb2.setBounds(150,135,100,50);
		rb3.setBounds(150,165,100,50);
		add(rb1);
		add(rb2);
		add(rb3);
		JButton b = new JButton("Start");
		b.setBounds(165,215,80,30);
		add(b);
		b.addActionListener(this);
		setBounds(250,200,500,500);
		setLayout(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
	public void actionPerformed(ActionEvent e){
		if(rb1.isSelected()){
			try{
				dispose();
				setVisible(false);
				new Sudoku(name,5,5,6,5,5,6,5,6,5);
			}catch(Exception e3){}
		}
		if(rb2.isSelected()){
			try{
				dispose();
				setVisible(false);
				new Sudoku(name,3,4,3,3,3,4,4,3,3);
			}catch(Exception e4){}
		}
		if(rb3.isSelected()){
			try{
				dispose();
				setVisible(false);
				new Sudoku(name,2,2,2,2,2,2,2,2,2);
			}catch(Exception e5){}
		}
	}
}
class Sudoku extends JFrame{
	final String NAME;
	final Date START = null;
	JButton b;
	JTextField tf[][];
	static int arr[][] = new int[9][9];
	static Random r = new Random();
	static void fillDiagonalBoxes(){
		for(int i=0;i<9;i=i+3){
			fillBox(i,i);
		}
	}
	static void fillBox(int rowpos,int colpos){
		for(int i=0;i<3;i++){
			for(int j=0;j<3;j++){
				int num;
				do{
					num = r.nextInt(9) + 1;
				}while(isInBox(rowpos,colpos,num));
				arr[rowpos+i][colpos+j] = num;
			}
		}
	}
	static boolean fillRemaining(int i,int j){
		if(i>=9 && j>=9){
			return true;
		}
		if(j>=9){
			if(i<9){
				i = i+1;
				j = 0;
			}
		}
		if(i<3){
			if(j<3){
				j = 3;
			}
		}
		else if(i<6){
			if(j==(int)(i/3)*3){
				j = j+3;
			}
		}
		else{
			if(j==6){
				i = i+1;
				j = 0;
				if(i>=9){
					return true;
				}
			}
		}
		for(int num=1;num<=9;num++){
			if(!isInColumn(j,num) && !isInRow(i,num) && !isInBox(i-i%3,j-j%3,num)){
				arr[i][j] = num;
				if(fillRemaining(i,j+1)){
					return true;
				}
				arr[i][j] = 0;
			}
		}
		return false;
	}
	static boolean isInColumn(int j,int num){
		for(int i=0;i<9;i++){
			if(arr[i][j]==num){
				return true;
			}
		}
		return false;
	}
	static boolean isInRow(int i,int num){
		for(int j=0;j<9;j++){
			if(arr[i][j]==num){
				return true;
			}
		}
		return false;
	}

	static boolean isInBox(int rowpos,int colpos,int num){
		 for(int i=0;i<3;i++){
		 	for(int j=0;j<3;j++){
		 		if(arr[rowpos+i][colpos+j]==num){
		 			return true;
		 		}
		 	}
		 }
		 return false;
	}
	public Sudoku(String name,int m1,int m2,int m3,int m4,int m5,int m6,int m7,int m8,int m9){
		NAME = name;
		fillDiagonalBoxes();
		fillRemaining(0,3);
		tf = new JTextField[9][9];
		final int [][]new_arr = arr;
		final int copy_arr[][] = arr;
		int g_i,g_j,g1=0,g2=0,g3=0,g4=0,g5=0,g6=0,g7=0,g8=0,g9=0;
		while(true){
			if(g1>=m1 && g2>=m2 && g3>=m3 && g4>=m4 && g5>=m5 && g6>=m6 && g7>=m7 && g8>=m8 && g9>=m9){
				break;
			}
			g_i = r.nextInt(9);
			g_j = r.nextInt(9);
			if(new_arr[g_i][g_j]!=0){
				if(g_i>=0  && g_i<=2  && g_j>=0  && g_j<=2 && g1<m1){
					new_arr[g_i][g_j] = 0;
					g1++;
				}
				if(g_i>=0  && g_i<=2  && g_j>=3  && g_j<=5 && g2<m2){
					new_arr[g_i][g_j] = 0;
					g2++;
				}
				if(g_i>=0  && g_i<=2  && g_j>=6  && g_j<=8 && g3<m3){
					new_arr[g_i][g_j] = 0;
					g3++;
				}
				if(g_i>=3  && g_i<=5  && g_j>=0  && g_j<=2 && g4<m4){
					new_arr[g_i][g_j] = 0;
					g4++;
				}
				if(g_i>=3  && g_i<=5  && g_j>=3  && g_j<=5 && g5<m5){
					new_arr[g_i][g_j] = 0;
					g5++;
				}
				if(g_i>=3  && g_i<=5  && g_j>=6  && g_j<=8 && g6<m6){
					new_arr[g_i][g_j] = 0;
					g6++;
				}
				if(g_i>=6  && g_i<=8  && g_j>=0  && g_j<=2 && g7<m7){
					new_arr[g_i][g_j] = 0;
					g7++;
				}
				if(g_i>=6  && g_i<=8  && g_j>=3  && g_j<=5 && g8<m8){
					new_arr[g_i][g_j] = 0;
					g8++;
				}
				if(g_i>=6  && g_i<=8  && g_j>=6  && g_j<=8 && g9<m9){
					new_arr[g_i][g_j] = 0;
					g9++;
				}
			}
		}	
		for(int i=0;i<9;i++){
			for(int j=0;j<9;j++){
				if(new_arr[i][j]==0){
					tf[i][j] = new JTextField();
					tf[i][j].setFont(new Font("Serif",Font.BOLD,15));
					tf[i][j].setHorizontalAlignment(JTextField.CENTER);
					tf[i][j].addActionListener(new ActionListener(){
						public void actionPerformed(ActionEvent e){
							int i1=0,j1=0,beg_i=0,beg_j=0,end_i=0,end_j=0;
							boolean checking;
							for(i1=0;i1<9;i1++){
								for(j1=0;j1<9;j1++){
									if(tf[i1][j1]==e.getSource()){
										break;
									}
								}
								if(j1<9){
									break;
								}
							}
							String box_value = tf[i1][j1].getText();
							try{
								int value = Integer.parseInt(box_value);
								System.out.println(value);
								if(value>=1 && value<=9){
									tf[i1][j1].setBackground(Color.WHITE);
									if(i1>=0 && i1<=2 && j1>=0 && j1<=2){
										beg_i = 0;
										beg_j = 0;
										end_i = 2;
										end_j = 2;
									}
									if(i1>=0 && i1<=2 && j1>=3 && j1<=5){
										beg_i = 0;
										beg_j = 3;
										end_i = 2;
										end_j = 5;
									}
									if(i1>=0 && i1<=2 && j1>=6 && j1<=8){
										beg_i = 0;
										beg_j = 6;
										end_i = 2;
										end_j = 8;
									}
									if(i1>=3 && i1<=5 && j1>=0 && j1<=2){
										beg_i = 3;
										beg_j = 0;
										end_i = 5;
										end_j = 2;
									}
									if(i1>=3 && i1<=5 && j1>=3 && j1<=5){
										beg_i = 3;
										beg_j = 3;
										end_i = 5;
										end_j = 5;
									}
									if(i1>=3 && i1<=5 && j1>=6 && j1<=8){
										beg_i = 3;
										beg_j = 6;
										end_i = 5;
										end_j = 8;
									}
									if(i1>=6 && i1<=8 && j1>=0 && j1<=2){
										beg_i = 6;
										beg_j = 0;
										end_i = 8;
										end_j = 2;
									}
									if(i1>=6 && i1<=8 && j1>=3 && j1<=5){
										beg_i = 6;
										beg_j = 3;
										end_i = 8;
										end_j = 5;
									}
									if(i1>=6 && i1<=8 && j1>=6 && j1<=8){
										beg_i = 6;
										beg_j = 6;
										end_i = 8;
										end_j = 8;
									}
									checking = false;
									for(int i2=beg_i;i2<=end_i;i2++){
										for(int j2=beg_j;j2<end_j;j2++){
											if(new_arr[i2][j2]==value){
												checking = true;
												break;
											}
										}
										if(checking) break;
									}
									if(!checking){
										for(int j2=0;j2<9;j2++){
											if(new_arr[i1][j2]==value){
												checking = true;
												break;
											}
										}
										if(!checking){
											for(int i2=0;i2<9;i2++){
												if(new_arr[i2][j1]==value){
													checking = true;
													break;
												}
											}
											if(!checking){
												new_arr[i1][j1] = value;
												for(int i2=0;i2<9;i2++){
													for(int j2=0;j2<9;j2++){
														if(new_arr[i2][j2]==0){
															checking = true;
															break;
														}
													}
													if(checking) break;
												}
												if(!checking){
														JOptionPane.showMessageDialog(null,NAME+" YOU WIN");
														try{
															dispose();
															setVisible(false);
														}catch(Exception e9){}

												}
											}
											else{
												tf[i1][j1].setBackground(Color.RED);
											}
										}
										else{
											tf[i1][j1].setBackground(Color.RED);
										}
									}
									else{
										tf[i1][j1].setBackground(Color.RED);
									}
								}
								else{
									JOptionPane.showMessageDialog(null,"integers 1-9 only");
									tf[i1][j1].setText("");
								}
							}catch(Exception rag){
								JOptionPane.showMessageDialog(null,"Enter integers only");
								tf[i1][j1].setText("");
							}
						}
					});
					add(tf[i][j]);
				}
				else{
					String str = Integer.toString(new_arr[i][j]);
					b = new JButton(str);
					add(b);
				}
			}
		}
		setBounds(250,200,500,500);
		setLayout(new GridLayout(9,9));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
}