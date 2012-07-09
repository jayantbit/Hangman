
package hangaman;

import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;





class w
{
String a,b;
public w(String x,String y){a=x;b=y;if(a.length()>0)fn();}
public void  fn()
{
try{
					
					RandomAccessFile rf= new  RandomAccessFile("scores.txt","rw");
					long l=(new File("scores.txt")).length();
					rf.seek(l);
					int k;
					for(int j=0;j<a.length();j++)
					{
					k=(int)(a.charAt(j));
					rf.writeInt(k+100);
					}
			        rf.writeInt((int)'\n'  +100);
					
					for(int j=0;j<b.length();j++)
					{
					k=(int)(b.charAt(j));
					rf.writeInt(k+100);
					}
			       rf.writeInt((int)'\n'  +100);
					
					
					rf.close();
					 }
					 catch(Exception e){System.out.println(e.getMessage());}
}
}


public class game  extends Applet   implements ActionListener
{
 
 
   
 
 int result=0,flag2=1,m,x=0,wrong=0,bkcol=0,fgcol=7,target,len,i=0,count=0,score=0,l_s=0,snd=1,nameadded=0;
 
 Panel mn;
 Button play=new Button("PLAY");
 Button reset=new Button("RESET");
 Button clear=new Button("CLEAR");
 Button clrscr=new Button("CLEAR SCORE");
 Button hscr=new Button("HIGHEST SCORES");
 Button addname=new Button("ADD NAMES");
 Button exit=new Button("NEW GAME");
 Button loadnew=new Button("NEW");
  
 
 TextField scr;
 
 JTextField l_score=new JTextField(12);
 JTextField l_name=new JTextField(12);
 Button l_submit=new Button("SUBMIT");
 Button l_high=new Button("HIGHEST SCORES");
 
 JFrame loose=new JFrame("GAME OVER");
 
 Panel keys,q,r,s,t;
 JFrame f;
 JFrame highscore=new JFrame("HIGH SCORERS");
 JTextArea hs=new JTextArea(); 
 
 TextField temp[];

 TextField wrongs[];
 Button btn[];
String fnames[],hint1[],name;
 
Color color[]={Color.pink,Color.pink,Color.cyan,
                       Color.darkGray,Color.gray,Color.green,
                       Color.lightGray,Color.magenta,Color.orange,
                      Color.black,Color.red,Color.white,Color.yellow};

String str[]={
               "A","B","C","D","E","F","G","H","I","J","K",
               "L","M","N","O","P","Q","R","S","T","U","V",
               "W","X","Y","Z"};


TextArea comment=new TextArea("HINTS");
Label hints=new Label(".........HINTS...........");


public void init()
{ 



    
setSize(580,80);
setBackground(color[bkcol]);
setLayout(new FlowLayout(FlowLayout.LEFT, 15, 15));
add(play);
add(reset);
add(clear);
add(hscr);
add(clrscr);
add(addname);

play.addActionListener(this);
hscr.addActionListener(this);
clrscr.addActionListener(this);
reset.addActionListener(this);
clear.addActionListener(this);
addname.addActionListener(this);




loose.setSize(200,200);
loose.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 15));
l_score.setEditable(false);

l_submit.addActionListener(this);
l_high.addActionListener(this);

loose.add(l_score);
loose.add(l_name);
loose.add(l_submit);
loose.add(l_high);
loose.setResizable (false);
loose.setLocation(20,60);
loose.getContentPane().setBackground(color[0]);



 
  keys=new Panel();
  q=new Panel();
  r= new Panel();
  s= new Panel();
  t= new Panel();
   
  r.setLayout(new GridLayout(1,5));
   q.setLayout(new FlowLayout(FlowLayout.CENTER));
   s.setLayout(new BorderLayout(5,5));
   t.setLayout(new BorderLayout());
   keys.setLayout(new GridLayout(2,13));
   
   scr=new TextField(2);
   

   wrongs=new TextField[5];
   for(i=0;i<5;i++)
   {wrongs[i]=new TextField(1);r.add(wrongs[i]);}


btn=new Button[str.length];
for( i=0;i<str.length;i++)
{ btn[i]=new Button(str[i]);
btn[i].addActionListener(this);
keys.add(btn[i]);
}
 
exit.addActionListener(this);



 highscore.setSize(300,300);
highscore.setLayout(new BorderLayout());
highscore.add(hs,BorderLayout.CENTER);
hs.setEditable(false);


init2();
}



public void init2()
{
    fnames=null;
    hint1=null;
    fnames=new String[100];
hint1=new String[100];
count=0;
try{
RandomAccessFile rf= new  RandomAccessFile("f1.txt","rw");
char ss;
int k;
boolean EOF=false;
String strr="";
while (!EOF) {
                try{ss=(char)(rf.readInt() -100);if(ss!='\n')strr+=ss;else {fnames[count++]=strr;strr="";}}
				catch (EOFException e) {
			EOF = true;
			}
			}
rf.close();			
			
			
rf= new  RandomAccessFile("f2.txt","rw");
EOF=false;
strr="";
k=0;

while (!EOF) {
                try{ss=(char)(rf.readInt() -100);if(ss!='\n')strr+=ss;else {hint1[k++]=strr;strr="";}}
				catch (EOFException e) {
			EOF = true;
			}
			}

rf.close();
}
catch(Exception e){}

set();

}

public void set()
{
   
   if(count==0)return;
    int rr=(int)(count * Math.random());
   name=fnames[rr];
   len=name.length();
   abc ob=new abc(name);
   m=ob.count();
   target=len-m+1;
   
   

  Panel p=new Panel();
  f=new JFrame("HANGMAN");
  temp =new TextField[100];
  Panel word[]=new Panel[10];
  
  for(i=0;i<len;i++)temp[i]=new TextField(1);
  for(i=0;i<10;i++)word[i]=new Panel();
  Panel guess=new Panel();


f.getContentPane().setBackground(color[bkcol]);
f.setForeground(color[fgcol]);
p.setLayout(new BorderLayout());
  int k=0,l=0;
   int z=0;
  String s2;
  for( i=0;i<m;i++)
  {s2=ob.pop();
   l=s2.length();
  z=k+l;
  word[i].setLayout(new GridLayout(1,l));
  for(;k<z;k++)
  word[i].add(temp[k]);
  guess.add(word[i]);
  k=z;
  }


loadnew.addActionListener(this);
s.add(r,BorderLayout.NORTH);
s.add(comment,BorderLayout.CENTER);
comment.setText(hint1[rr]);
s.add(hints,BorderLayout.SOUTH);

q.add(keys);
p.add(q,BorderLayout.NORTH);
p.add(guess,BorderLayout.SOUTH);
t.add(scr,BorderLayout.NORTH);
t.add(exit,BorderLayout.SOUTH);

f.add(p,BorderLayout.CENTER);
f.add(s,BorderLayout.WEST);
f.add(t,BorderLayout.EAST);
f.setSize(1250,300);
f.setResizable (false);


/*
f.addWindowListener(new WindowAdapter(){
  public void windowClosing(WindowEvent we){
  f=null;
  }});
  
  */

scr.setText((new Integer(score)).toString());
}

public void end()
{ flag2=1;result=0;wrong=0;x=0;
int num,y=0;
for(num=0;num<5;num++)
wrongs[num].setText("");

for(num=0;num<26;num++)
btn[num].setLabel(str[num]);

for(num=0;num<len;num++)
{ if(name.charAt(num)==32)y++;
temp[num-y].setText("");
}
}

public void hsc()
{
	
    int k=1,pc=0,x,yy,temp;
String p_name[]=new String[100],t_s;
int p_score[]=new int[100];
try{
RandomAccessFile rf= new  RandomAccessFile("scores.txt","rw");
char cs;
boolean EOF=false;




String str="";
while (!EOF) {
                try{cs=(char)(rf.readInt()-100);if(cs!='\n')str+=cs;else {
																			
																			if(str.length()>0)
																			{
																			if(k==1)p_name[pc]=str;
																			else {p_score[pc]=Integer.parseInt(str);pc++;}
																		    str="";
																			k*=-1;
																			
																			}
																		  
																		  }
																			
				    }
				catch (EOFException e) {
			EOF = true;
			}
			}
				

rf.close();
}
catch(Exception e){}


for(x=0;x<pc-1;x++)
for(yy=x+1;yy<pc;yy++)
if(p_score[yy]>p_score[x])
{

temp=p_score[x];
p_score[x]=p_score[yy];
p_score[yy]=temp;

t_s=p_name[x];
p_name[x]=p_name[yy];
p_name[yy]=t_s;
}


String res="";

for(x=0;x<pc;x++)res+=p_name[x]+"\t\t"+p_score[x]+"\n";
hs.setText(res);
highscore.show();
}

public void wait2()
{
    int i;
    for(i=0;i<100000;i++){;}
}


public  synchronized void playSound( final String url) {
new Thread(new Runnable() {
  public void run() {
    try {
      
        
      
    InputStream in =new FileInputStream(url);
    AudioPlayer.player.start(new AudioStream(in));
    
    } catch (Exception e) {
      System.err.println(e.getMessage());
    }
  }
}).start();
}

public void actionPerformed(ActionEvent ae)
{ 
if(snd==1){
               playSound("click.au");
    
                    }
String s=ae.getActionCommand();
 if(s.equals("NEW GAME"))
{ 
    if(f!=null)f.dispose();

if(result==-1)loose.dispose();
end();
end();
set();
if(count!=0)f.show();
}
else if(s.equals("NEW"))
{end();if(f!=null)f.dispose();
f=null;set();

}

else if(s.equals("PLAY"))
{ if(nameadded==1){init2();nameadded=0;}
    if(count!=0)f.show();
}
else if(s.equals("SUBMIT"))
{

new w(l_name.getText(),String.valueOf(l_s));
loose.dispose();
hsc();
}
else if(s.equals("HIGHEST SCORES"))
{
hsc();
}
else if(s.equals("CLEAR SCORE")){
    new reset_score();
}
else if(s.equals("CLEAR")){
    if(f!=null)f.dispose();f=null;
    new clear();
    init2();
    
}
else if(s.equals("RESET")){
    if(f!=null)f.dispose();f=null;
    new reset();
    init2();
    
}

else if(s.equals("ADD NAMES")){
   if(f!=null)f.dispose();f=null;
    adder ob= new adder();
   ob.fn2();
   nameadded=1;   
}
 
else if(s.length()>0)
{
 char c[]=s.toCharArray();
 char ch=c[0];
 int num=(int)ch;
 num-=65;
 if(flag2==1)btn[num].setLabel("");
 
 int y=0,z=0;
 int flag=1;

for(z=0;z<len && wrong<5;z++)
{
if(name.charAt(z)==32){y++;}
if(name.charAt(z)==ch)
{
temp[z-y].setText(s);
flag=0;
x++;
if(x==target)
{flag2=0;result=1;comment.setText("SCORE + = 5");
if(snd==1)playSound("b.au");
score+=5;scr.setText((new Integer(score)).toString());

JOptionPane.showMessageDialog(null,"YOU WIN");
}
}

}

if(flag==1 && x<target){wrongs[wrong++].setText("X");
if(snd==1)
playSound("incorrect.au");
}
 
if(wrong==5 && x<target)
{flag2=0;result=-1;l_score.setText("SCORE = "+score);l_s=score;score=0;

String cc;
for(int cz=0,cy=0;cz<len;cz++)
{
if(name.charAt(cz)==32)cy++;
else {
		cc=""+name.charAt(cz);
		temp[cz-cy].setText(cc);
	   }
}
loose.show();
}
}
}

public void itemStateChanged(ItemEvent e)
{
 repaint();
}

public static void main(String args[]) {  
        
         JFrame appletFrame = new JFrame("HANGMAN");  
       
         appletFrame.setSize(580,80);  
        appletFrame.addWindowListener(new WindowAdapter(){
  public void windowClosing(WindowEvent we){
  System.exit(0);
  }
  });
          
       
        Applet myApplet = new game();  
        appletFrame.add(myApplet);  
        myApplet.init();  
        myApplet.start();
        appletFrame.setResizable (false);;
        appletFrame.show();  
 }
 

}


  

class abc
{ int start=0, end=0,words=0;
String s[]=new String[25];
char n[];
String name;

abc(String nm)
{ name=nm;
 int l= name.length(),k;
 int i=0,pre=0;
 String temp;
 
  n=name.toCharArray();
 
 for(i=pre;i<l;i++)
 if(n[i]==32 || i==l-1)
 { k=i-pre;
 if(i==l-1)k++;
   temp=String.copyValueOf(n,pre,k);
   push(temp); words++;
 pre=i+1;
 }
 

}
public void set(String nm)
{name=nm;}

public void push(String temp)
{ s[end++]=temp;}
 public String pop()
{ return s[start++];}

int count()
{return words;}
}



 


