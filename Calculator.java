/*
  ������ƣ����׼�����
      ���ߣ�������
 */
import java.awt.*;
import java.awt.Color;
import javax.swing.ImageIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

//Calculator�࣬�̳�JFrame��ܣ�ʵ���¼��������ӿ�
public class Calculator extends JFrame implements ActionListener {
    private String[] KEYS = { "7", "8", "9", "AC", "4", "5", "6", "-", "1", "2", "3", "+", "0",".", "��","��","%", "��","��","="};
    private JButton keys[] = new JButton[KEYS.length];
    private JButton button = new JButton("�����ʷ��¼");
    private JTextArea resultText = new JTextArea("0.0");// �ı������TextArea�����ɶ����ı����ı������ݳ�ʼֵ��Ϊ0.0
    private JTextArea History = new JTextArea();// ��ʷ��¼�ı����ʼֵ��Ϊ��
    private JTextArea Notebook = new JTextArea("�� ��� ��\n��ӭʹ�ü�����");
    private JScrollPane hy0=new JScrollPane(Notebook);//����ʷ��¼�ı����½�һ����ֱ��������
    private JScrollPane hy1=new JScrollPane(resultText);
    private JScrollPane hy2=new JScrollPane(History);
    private JPanel jp0=new JPanel();
    private JPanel jp1=new JPanel();
    private JPanel jp2=new JPanel();
    private Font NotebookFn = new Font("����",Font.PLAIN,18);
    private Font KeyFn = new Font("����",Font.PLAIN,25);
    private String b = "";

    // ���췽��
    public Calculator() {

	this.setIconImage(new ImageIcon("G://java//jsq001.png").getImage());
	button.setBounds(290,125,249,20);
	button.setVisible(true);
	button.setBackground(Color.lightGray);
	Notebook.setBounds(20,20,520,100);
	Notebook.setEditable(true);
	Notebook.setAlignmentX(LEFT_ALIGNMENT);
	Notebook.setFont(NotebookFn);
	resultText.setBounds(290,150,250, 40);// �����ı����С
	resultText.setAlignmentX(RIGHT_ALIGNMENT);// �ı��������Ҷ���
	resultText.setEditable(false);// �ı��������޸Ľ��
	resultText.setFont(NotebookFn);
	History.setBounds(290, 198, 250,173);// �����ı����С
	History.setAlignmentX(LEFT_ALIGNMENT);// �ı��������Ҷ���
	History.setEditable(false);// �ı��������޸Ľ��
	History.setFont(NotebookFn);
	jp0.setBounds(20,20,520,100);//������崰��λ�ü���С
	jp0.setLayout(new GridLayout());
	jp1.setBounds(290,150,250, 40);//������崰��λ�ü���С
	jp1.setLayout(new GridLayout());
	jp2.setBounds(290, 198, 250,173);//������崰��λ�ü���С
	jp2.setLayout(new GridLayout());	
	resultText.setLineWrap(true);// �����Զ����й���
	resultText.setWrapStyleWord(true);// ������в����ֹ���
	History.setLineWrap(true);//�Զ�����
	History.setWrapStyleWord(true);
	History.setSelectedTextColor(Color.blue);
	Notebook.setLineWrap(true);
	Notebook.setWrapStyleWord(true);
	hy0.setViewportView(Notebook);
	hy1.setViewportView(resultText);//ʹ��������ʾ����
	hy2.setViewportView(History);
	hy0.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);//�����ô�ֱ������һֱ��ʾ
	hy1.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);//�����ô�ֱ������һֱ��ʾ
	hy2.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);//������ˮƽ������һֱ��ʾ
	hy2.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
	jp0.add(hy0);
	jp1.add(hy1);
	jp2.add(hy2);
	this.add(jp0);
	this.add(jp1);//�������ӵ��ܴ�����
	this.add(jp2);//�������ӵ��ܴ�����
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
	//ɾ����ʷ��¼
	button.addActionListener(new ActionListener(){
	
		public void actionPerformed(ActionEvent e){
		
			History.setText("");
		}
	});
	for (int i = 0; i < KEYS.length; i++)// ÿ����ť��ע���¼�������
	{
	    keys[i].addActionListener(this);
	    keys[i].setBackground(Color.lightGray);
	}
	this.setResizable(false);
	this.setBounds(600, 290, 575, 425);
	this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	this.setVisible(true);
    }
	// �¼�����
	public void actionPerformed (ActionEvent e){

	String label=e.getActionCommand();	//����¼�Դ�ı�ǩ
	if(label=="=")
	{
	    resultText.setText(this.b);
	    History.setText(History.getText()+resultText.getText());
	    if(label=="=")				//���ü��㷽�����ó����ս��
	    {
		String s[]=houzhui(this.b);
		String result=Result(s);
		this.b=result+"";
		//�����ı��򣬵�ǰ������ַ���b�У���δɾ������һ��������Ŵ˽����ʵ����������
		resultText.setText(this.b);
		History.setText(History.getText()+"="+resultText.getText()+"\n");
	    }
	}
	 else if(label=="AC")				//��հ�ť��������ʾ���ı���ǰ�����е�����ͽ��
	    {
		this.b="";
		 resultText.setText("0");		//�����ı������ʾ����ʾ��ʼֵ;
	    }
	else if(label == "��")
	{
		String n = Sqrt (this.b);
		resultText.setText("sqrt"+this.b+"="+n);
		History.setText(History.getText()+"sqrt"+this.b+"="+n+"\n");
		this.b = n;
	}
	else if(label.equals("��")) //ʵ�ֻ���
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
	
	//����׺���ʽת��Ϊ��׺���ʽ
    	private String[] houzhui(String str) {
	String s = "";	// ���ڳнӶ�λ�����ַ���
	char Stack[] = new char[100];	// ��̬ջ,���û�����Ĳ��������д������ڴ洢�����
	String Queue[] = new String[100];	// ��׺���ʽ�ַ������飬Ϊ�˽���λ���洢Ϊ�������ַ���
	int top = -1, j = 0;	// ��ָ̬��top,���Ʊ���j
	for (int i = 0; i < str.length(); i++)	// ������׺���ʽ
	// indexof�����������ִ��״γ��ֵ�λ�ã�charAt��������indexλ�ô����ַ���
	{
	    if ("0123456789.".indexOf(str.charAt(i)) >= 0) // ���������ַ������
	    {
		s = "";	 	// ��Ϊ�н��ַ���ÿ�ο�ʼʱ��Ҫ���
		for (; i < str.length() && "0123456789.".indexOf(str.charAt(i)) >= 0; i++) {
		    s = s + str.charAt(i);
		}
		i--;
		Queue[j] = s;			// �����ַ�ֱ�Ӽ����׺���ʽ
		j++;
	    } 
	    if ("��%��".indexOf(str.charAt(i)) >= 0)	// ���������ȼ������
	    {
		if (top == -1) {			// ��ջΪ����ֱ����ջ
		    top++;
		    Stack[top] = str.charAt(i);
		} else {				// ջ��Ϊ��,��ջ�е�����Ԫ����ӣ�ֱ��ջ��Ԫ�����ȼ�С��x����ջΪ��
		    if ("��%��".indexOf(Stack[top]) >= 0) {
			// ջ��Ԫ��ҲΪ�����ȼ������
			Queue[j] = Stack[top] + "";	// ջ��Ԫ�س�ջ�����׺���ʽ
			j++;
			Stack[top] = str.charAt(i);		// ��ǰ�������ջ
		    }else if ("+-".indexOf(str.charAt(i)) >= 0) {	// ���������ȼ������
			Queue[j] = Stack[top] + "";		// ջ��Ԫ�س�ջ���������ʽ
			j++;
			Stack[top] = str.charAt(i);			// ��ǰԪ����ջ
		    }
		}
	    } else if ("+-".indexOf(str.charAt(i)) >= 0) {
		if (top == -1) {
		    top++;
		    Stack[top] = str.charAt(i);
		} else {
		    if ("��%��".indexOf(Stack[top]) >= 0) {
			// ջ��Ԫ��ҲΪ�����ȼ������
			Queue[j] = Stack[top] + "";		// ջ��Ԫ�س�ջ�����׺���ʽ
			j++;
			Stack[top] = str.charAt(i);			// ��ǰ�������ջ
		    } else if ("+-".indexOf(str.charAt(i)) >= 0) {	// ���������ȼ������
			Queue[j] = Stack[top] + "";		// ջ��Ԫ�س�ջ���������ʽ
			j++;
			Stack[top] = str.charAt(i);			// ��ǰԪ����ջ
		    }
		}
	    }
	}
	for (; top != -1;) {						// ����������ջ��ʣ��Ԫ�����γ�ջ�����׺���ʽ
	    Queue[j] = Stack[top] + "";
	    j++;
	    top--;
	}
	return Queue;
    }

//�������㷽��
    public String Sqrt(String str) {
	String result = "";
	double a = Double.parseDouble(str), b = 0;
	b = Math.sqrt(a);
	result = String.valueOf(b);//��������ת��Ϊstring���Ͳ�����string���͵ı���result
	return result;
    }
    
// �����׺���ʽ�����������ս��
    public String Result(String str[]) {
	String Result[] = new String[100];		// ˳��洢��ջ����������Ϊ�ַ���
	int Top = -1;					// ��ָ̬��Top
	for (int i = 0; str[i] != null; i++) {
	    if ("+-��%��".indexOf(str[i]) < 0) {
		Top++;
		Result[Top] = str[i];
	    }
	    if ("+-��%��".indexOf(str[i]) >= 0)		// ����������ַ�����ջ������Ԫ�س�ջ���㲢���������ջ��
	    {
		double x, y, n;
		x = Double.parseDouble(Result[Top]);	// ˳���ջ���������ַ�������ת��Ϊdouble����
		Top--;
		y = Double.parseDouble(Result[Top]);
		Top--;
		if ("-".indexOf(str[i]) >= 0) {
		    n = y - x;
		    Top++;
		    Result[Top] = String.valueOf(n);	// ��������������ջ
		}
		if ("+".indexOf(str[i]) >= 0) {
		    n = y + x;
		    Top++;
		    Result[Top] = String.valueOf(n);	// ��������������ջ
		}
		if ("��".indexOf(str[i]) >= 0) {
		    n = y * x;
		    Top++;
		    Result[Top] = String.valueOf(n);	// ��������������ջ

		}
		if ("��".indexOf(str[i]) >= 0)
		{
		    if (x == 0)				// ������������Ϊ0
		    {
			String s = "error!";
			return s;
		    } else {
			n = y / x;
			Top++;
			Result[Top] = String.valueOf(n);// ��������������ջ
		    }
		}
		if ("%".indexOf(str[i]) >= 0) 
		{
		    if (x == 0)				// ������������Ϊ0
		    {
			String s = "error!";
			return s;
		    } else {
			n = y % x;
			Top++;
			Result[Top] = String.valueOf(n);// ��������������ջ
		    }
		}
		
	    }
	}
	return Result[Top];				// �������ս��
    }

    // ������
    public static void main(String[] args) {
	Calculator a = new Calculator();
    }
}
