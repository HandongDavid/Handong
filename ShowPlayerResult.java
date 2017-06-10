package oodp;

import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;

public class ShowPlayerResult extends JFrame { //��ġ��忡�� �ش� ��¥�� �������� �Ʒ� ����� �� �� �ִ� â
	
	private JTable table_1;
	PlayerResultTable prt = new PlayerResultTable();	
	ArrayList<PlayerResult> playerList;
	
	public ShowPlayerResult(int year,int month,int day) { //�������� �Ʒ� ��� ����Ʈ â ����
		
		setTitle("���� �Ʒ� ���");
		setSize(700, 448);
		setLocation(350, 250);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);

		
		playerList = prt.playerResultAll(year, month, day);
		
		JTable table = null;
		String[] column =  {
			"�̸�", "Ű", "������", "�����", "���ο�������", "��", "�ð�"
		};
		Object[][] ob = new Object[playerList.size()][7];
		
		
		PlayerResultList prl = new PlayerResultList();
		Iterator it = prl.iterator();
		
		
		prl.setPlayerResult(playerList);
		
		
		int j = 0;
		while(it.hasNext())
		{
			PlayerResult pl = (PlayerResult)it.next();
			
			ob[j][0] = pl.getUser().getName();
			ob[j][1] = pl.getUser().getCm();
			ob[j][2] = pl.getUser().getKg();
			ob[j][3] = pl.getPMexer();
			ob[j][4] = pl.getPexer();
			ob[j][5] = pl.getPnum();
			ob[j][6] = pl.getTime();

			j++;
		}

		
		
		table= new JTable(ob,column);
		table.getColumn("�̸�").setPreferredWidth(60);
		table.getColumn("Ű").setPreferredWidth(60);
		table.getColumn("������").setPreferredWidth(60);
		table.getColumn("�����").setPreferredWidth(100);
		table.getColumn("���ο�������").setPreferredWidth(100);
		table.getColumn("��").setPreferredWidth(100);
		table.getColumn("�ð�").setPreferredWidth(200);
		DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
		dtcr.setHorizontalAlignment(SwingConstants.CENTER);
		TableColumnModel tcm = table.getColumnModel();
		for(int i=0; i<tcm.getColumnCount(); i++){
			tcm.getColumn(i).setCellRenderer(dtcr);
		}
		
		table.setSize(660,387);
		
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setSize(660, 387);	
		scrollPane.setLocation(12, 10);
		scrollPane.setPreferredSize(new Dimension(369, 203));
		add(scrollPane);
		
		setVisible(true);
	}
	

}
