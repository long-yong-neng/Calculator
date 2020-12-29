/*
  软件名称：简易计算器
      作者：龙勇能
 */
import java.awt.*;
import java.awt.Color;
import javax.swing.ImageIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

//Calculator类，继承JFrame框架，实现事件监听器接口
public class Calculator extends JFrame implements ActionListener {
    private String[] KEYS = { "7", "8", "9", "AC", "4", "5", "6", "-", "1", "2", "3", "+", "0",".", "÷","×","%", "√","←","="};
    private JButton keys[] = new JButton[KEYS.length];
    private JButton button = new JButton("清除历史记录");
    private JTextArea resultText = new JTextArea("0.0");// 文本域组件TextArea可容纳多行文本；文本框内容初始值设为0.0
    private JTextArea History = new JTextArea();// 历史记录文本框初始值设为空
    private JTextArea Notebook = new JTextArea("嗨 你好 ！\n欢迎使用计算器");
    private JScrollPane hy0=new JScrollPane(Notebook);//给历史记录文本域新建一个垂直滚动滑条
    private JScrollPane hy1=new JScrollPane(resultText);
    private JScrollPane hy2=new JScrollPane(History);
    private JPanel jp0=new JPanel();
    private JPanel jp1=new JPanel();
    private JPanel jp2=new JPanel();
    private Font NotebookFn = new Font("宋体",Font.PLAIN,18);
    private Font KeyFn = new Font("宋体",Font.PLAIN,25);
    private String b = "";

    // 构造方法
    public Calculator() {

	this.setIconImage(new ImageIcon("G://java//jsq001.png").getImage());
	button.setBounds(290,125,249,20);
	button.setVisible(true);
	button.setBackground(Color.lightGray);
	Notebook.setBounds(20,20,520,100);
	Notebook.setEditable(true);
	Notebook.setAlignmentX(LEFT_ALIGNMENT);
	Notebook.setFont(NotebookFn);
	resultText.setBounds(290,150,250, 40);// 设置文本框大小
	resultText.setAlignmentX(RIGHT_ALIGNMENT);// 文本框内容右对齐
	resultText.setEditable(false);// 文本框不允许修改结果
	resultText.setFont(NotebookFn);
	History.setBounds(290, 198, 250,173);// 设置文本框大小
	History.setAlignmentX(LEFT_ALIGNMENT);// 文本框内容右对齐
	History.setEditable(false);// 文本框不允许修改结果
	History.setFont(NotebookFn);
	jp0.setBounds(20,20,520,100);//设置面板窗口位置及大小
	jp0.setLayout(new GridLayout());
	jp1.setBounds(290,150,250, 40);//设置面板窗口位置及大小
	jp1.setLayout(new GridLayout());
	jp2.setBounds(290, 198, 250,173);//设置面板窗口位置及大小
	jp2.setLayout(new GridLayout());	
	resultText.setLineWrap(true);// 激活自动换行功能
	resultText.setWrapStyleWord(true);// 激活断行不断字功能
	History.setLineWrap(true);//自动换行
	History.setWrapStyleWord(true);
	History.setSelectedTextColor(Color.blue);
	Notebook.setLineWrap(true);
	Notebook.setWrapStyleWord(true);
	hy0.setViewportView(Notebook);
	hy1.setViewportView(resultText);//使滚动条显示出来
	hy2.setViewportView(History);
	hy0.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);//设置让垂直滚动条一直显示
	hy1.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);//设置让垂直滚动条一直显示
	hy2.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);//设置让水平滚动条一直显示
	hy2.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
	jp0.add(hy0);
	jp1.add(hy1);
	jp2.add(hy2);
	this.add(jp0);
	this.add(jp1);//将面板添加到总窗体中
	this.add(jp2);//将面板添加到总窗体中
	this.add(button);
	
	this.setLayout(null);
	this.setTitle("Calculator");	

	int x = 20, y =150;
	for (int i = 0; i < KEYS.length; i++)
	{
	    keys[i] = new JButton();
	    keys[i].setText(KEYS[i]);
	    keys[i].setBounds(x, y, 60, 40);
	    keys[i].setFont(KeyFn);
	    if (x < 215) {
		x += 65;
	    } else {
		x = 20;
		y += 45;
	    }
	    this.add(keys[i]);
	}
	//删除历史记录
	button.addActionListener(new ActionListener(){
	
		public void actionPerformed(ActionEvent e){
		
			History.setText("");
		}
	});
	for (int i = 0; i < KEYS.length; i++)// 每个按钮都注册事件监听器
	{
	    keys[i].addActionListener(this);
	    keys[i].setBackground(Color.lightGray);
	}
	this.setResizable(false);
	this.setBounds(600, 290, 575, 425);
	this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	this.setVisible(true);
    }
	// 事件处理
	public void actionPerformed (ActionEvent e){

	String label=e.getActionCommand();	//获得事件源的标签
	if(label=="=")
	{
	    resultText.setText(this.b);
	    History.setText(History.getText()+resultText.getText());
	    if(label=="=")				//调用计算方法，得出最终结果
	    {
		String s[]=houzhui(this.b);
		String result=Result(s);
		this.b=result+"";
		//更新文本框，当前结果在字符串b中，并未删除，下一次输入接着此结果以实现连续运算
		resultText.setText(this.b);
		History.setText(History.getText()+"="+resultText.getText()+"\n");
	    }
	}
	 else if(label=="AC")				//清空按钮，消除显示屏文本框前面所有的输入和结果
	    {
		this.b="";
		 resultText.setText("0");		//更新文本域的显示，显示初始值;
	    }
	else if(label == "√")
	{
		String n = Sqrt (this.b);
		resultText.setText("sqrt"+this.b+"="+n);
		History.setText(History.getText()+"sqrt"+this.b+"="+n+"\n");
		this.b = n;
	}
	else if(label.equals("←")) //实现回退
	{
		if(resultText.getText().length()>0)
		{
			this.b = "";
			resultText.setText(resultText.getText().substring(0,resultText.getText().length()-1));
			
		}

	}
	else
	{
	    this.b=this.b+label;
	    resultText.setText(this.b);
	       
	}
}
	
	//将中缀表达式转换为后缀表达式
    	private String[] houzhui(String str) {
	String s = "";	// 用于承接多位数的字符串
	char Stack[] = new char[100];	// 静态栈,对用户输入的操作符进行处理，用于存储运算符
	String Queue[] = new String[100];	// 后缀表达式字符串数组，为了将多位数存储为独立的字符串
	int top = -1, j = 0;	// 静态指针top,控制变量j
	for (int i = 0; i < str.length(); i++)	// 遍历中缀表达式
	// indexof函数，返回字串首次出现的位置；charAt函数返回index位置处的字符；
	{
	    if ("0123456789.".indexOf(str.charAt(i)) >= 0) // 遇到数字字符的情况
	    {
		s = "";	 	// 作为承接字符，每次开始时都要清空
		for (; i < str.length() && "0123456789.".indexOf(str.charAt(i)) >= 0; i++) {
		    s = s + str.charAt(i);
		}
		i--;
		Queue[j] = s;			// 数字字符直接加入后缀表达式
		j++;
	    } 
	    if ("×%÷".indexOf(str.charAt(i)) >= 0)	// 遇到高优先级运算符
	    {
		if (top == -1) {			// 若栈为空则直接入栈
		    top++;
		    Stack[top] = str.charAt(i);
		} else {				// 栈不为空,把栈中弹出的元素入队，直到栈顶元素优先级小于x或者栈为空
		    if ("×%÷".indexOf(Stack[top]) >= 0) {
			// 栈顶元素也为高优先级运算符
			Queue[j] = Stack[top] + "";	// 栈顶元素出栈进入后缀表达式
			j++;
			Stack[top] = str.charAt(i);		// 当前运算符入栈
		    }else if ("+-".indexOf(str.charAt(i)) >= 0) {	// 遇到低优先级运算符
			Queue[j] = Stack[top] + "";		// 栈顶元素出栈进入后最表达式
			j++;
			Stack[top] = str.charAt(i);			// 当前元素入栈
		    }
		}
	    } else if ("+-".indexOf(str.charAt(i)) >= 0) {
		if (top == -1) {
		    top++;
		    Stack[top] = str.charAt(i);
		} else {
		    if ("×%÷".indexOf(Stack[top]) >= 0) {
			// 栈顶元素也为高优先级运算符
			Queue[j] = Stack[top] + "";		// 栈顶元素出栈进入后缀表达式
			j++;
			Stack[top] = str.charAt(i);			// 当前运算符入栈
		    } else if ("+-".indexOf(str.charAt(i)) >= 0) {	// 遇到低优先级运算符
			Queue[j] = Stack[top] + "";		// 栈顶元素出栈进入后最表达式
			j++;
			Stack[top] = str.charAt(i);			// 当前元素入栈
		    }
		}
	    }
	}
	for (; top != -1;) {						// 遍历结束后将栈中剩余元素依次出栈进入后缀表达式
	    Queue[j] = Stack[top] + "";
	    j++;
	    top--;
	}
	return Queue;
    }

//开方运算方法
    public String Sqrt(String str) {
	String result = "";
	double a = Double.parseDouble(str), b = 0;
	b = Math.sqrt(a);
	result = String.valueOf(b);//将运算结果转换为string类型并赋给string类型的变量result
	return result;
    }
    
// 计算后缀表达式，并返回最终结果
    public String Result(String str[]) {
	String Result[] = new String[100];		// 顺序存储的栈，数据类型为字符串
	int Top = -1;					// 静态指针Top
	for (int i = 0; str[i] != null; i++) {
	    if ("+-×%÷".indexOf(str[i]) < 0) {
		Top++;
		Result[Top] = str[i];
	    }
	    if ("+-×%÷".indexOf(str[i]) >= 0)		// 遇到运算符字符，将栈顶两个元素出栈计算并将结果返回栈顶
	    {
		double x, y, n;
		x = Double.parseDouble(Result[Top]);	// 顺序出栈两个数字字符串，并转换为double类型
		Top--;
		y = Double.parseDouble(Result[Top]);
		Top--;
		if ("-".indexOf(str[i]) >= 0) {
		    n = y - x;
		    Top++;
		    Result[Top] = String.valueOf(n);	// 将运算结果重新入栈
		}
		if ("+".indexOf(str[i]) >= 0) {
		    n = y + x;
		    Top++;
		    Result[Top] = String.valueOf(n);	// 将运算结果重新入栈
		}
		if ("×".indexOf(str[i]) >= 0) {
		    n = y * x;
		    Top++;
		    Result[Top] = String.valueOf(n);	// 将运算结果重新入栈

		}
		if ("÷".indexOf(str[i]) >= 0)
		{
		    if (x == 0)				// 被除数不允许为0
		    {
			String s = "error!";
			return s;
		    } else {
			n = y / x;
			Top++;
			Result[Top] = String.valueOf(n);// 将运算结果重新入栈
		    }
		}
		if ("%".indexOf(str[i]) >= 0) 
		{
		    if (x == 0)				// 被除数不允许为0
		    {
			String s = "error!";
			return s;
		    } else {
			n = y % x;
			Top++;
			Result[Top] = String.valueOf(n);// 将运算结果重新入栈
		    }
		}
		
	    }
	}
	return Result[Top];				// 返回最终结果
    }

    // 主函数
    public static void main(String[] args) {
	Calculator a = new Calculator();
    }
}
