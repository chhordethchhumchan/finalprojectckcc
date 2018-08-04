package AdvanceProject.HotelReservation;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.*;
import java.awt.*;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import restaurant.Booking;
import restaurant.BookingDetail;
import restaurant.Food;
import restaurant.Tables;

/**
 * Hello world!
 *
 */
public class MainRun extends JFrame implements ActionListener
{	
	private List<String> getListTableReserved = new ArrayList<String>();
	private String[] listTableIDFree;
	private int reservationID;
	private String[] ta;
	private String str;
	
	JMenuBar menuBar;
	JMenu mFile, mTable, mFood, mReservation, mOrder, mHelp;
	// For Menu File
	JMenuItem mItemNew, mItemSave, mItemExit, mItemEdit;
	// For Menu Table
	JMenuItem mItemNewTable, mItemListTable;
	// For Menu Food
	JMenuItem mItemNewFood, mItemListFood;
	// For Menu Order
	JMenuItem mItemListOrder, mItemTakeOrder;
	// For Menu Reservation;
	JMenuItem mItemTakeReservation, mItemReservationList, mItemInvoice;
	// For Menu Help;
	JMenuItem mItemWelcome, mItemHelpContents, mItemCheckUpdate, mItemAbout;
	// JTree
	JTree jTreeRestaurant;
	JTabbedPane jTab;
	
	// Table Item
	JButton btnTableSave, btnTableClear, btnTableEdit, btnTableDelete, btnSearchTable, btnTableUpdate;
	JTextField txtTableID, txtTableSeats, txtTableStatus, txtSearchTable;
	DefaultTableModel tbModel;
	JTable tbListTable;
	
	// Food Item
	JButton btnFoodSave, btnFoodClear, btnFoodEdit, btnFoodDelete, btnFoodUpdate, btnSearchFood;
	JTextField txtFoodID, txtFoodName, txtFoodPrice, txtSearchFood;
	DefaultTableModel tbFoodModel;
	JTable tbFood;
	
	// Order Item
	JButton btnOrderSave, btnOrderClear, btnOrderEdit, btnOrderDelete, btnOrderUpdate, btnSearchOrder;
	JTextField txtOrderID, txtOrderTableID, txtOrderFoodId, txtSearchOrder;
	DefaultTableModel tbOrderModel; 
	JTable tbOrder;
	
	// Reservation Item
	JButton btnReservationSave, btnReservationClear, btnReservationEdit, btnReservationDelete, btnReservationUpdate,btnSearchReservation;
	JTextField txtReservationID, txtCustomerName, txtCustomerNumber, txtReservationTableNumber, txtSearchReservation;
	JList jcbReservationTableID;
	JComboBox jcbbReservationTableID;
	DefaultTableModel tbReservationModel;
	JTable tbReservation;
	JComboBox jbcOrderTableID,jbcOrderFoodID;
	
	// Invoice Item
	
	
	public MainRun() {
		
		mItemNew = new JMenuItem("New");
		mItemExit = new JMenuItem("Exit");
		mItemSave = new JMenuItem("Save");
		mItemEdit = new JMenuItem("Edit");
		//Create object Menu File and add its items
		mFile = new JMenu("File");
		mFile.add(mItemNew);
		mFile.add(mItemEdit);

		mFile.add(new JSeparator());
		mFile.add(mItemSave);
		mFile.add(mItemExit);
		//==========================END MENU FILE =====================/
		
		//Create Object Menu Item of Table Mgt
		mItemNewTable = new JMenuItem("New Table");
		mItemNewTable.addActionListener(this);
		mItemListTable = new JMenuItem("List Table");
		mItemListTable.addActionListener(this);
		
		//Create Object Menu Table Mgt and add its items
		mTable = new JMenu("Table Info");
		mTable.add(mItemNewTable);
		mTable.add(mItemListTable);
		//==========================END MENU Table ==================/
		
		//Create Object Menu Item of Food
		mItemNewFood = new JMenuItem("New Food");
		mItemNewFood.addActionListener(this);

		mItemListFood = new JMenuItem("List Food");
		mItemListFood.addActionListener(this);
		
		
		//Create Object Menu Report add add its items
		mFood = new JMenu("Food Info");
		mFood.add(mItemNewFood);
		mFood.add(mItemListFood);
		
		// ==========================================================
		mItemTakeOrder = new JMenuItem("Take Order");
		mItemTakeOrder.addActionListener(this);
		mItemListOrder = new JMenuItem("List Order");
		mItemListOrder.addActionListener(this);
		mOrder = new JMenu("Order");
		mOrder.add(mItemTakeOrder);
		mOrder.add(mItemListOrder);
		
		//=========================END MENU Food ======================/
		//Create Object Menu item of Order Rule
		mItemTakeReservation = new JMenuItem("Take Reservation");
		mItemTakeReservation.addActionListener(this);
		mItemReservationList = new JMenuItem("List Reservation");
		mItemReservationList.addActionListener(this);
		//Create Object menu reservation list and add its items
		mReservation = new JMenu("Reservation");
		mReservation.add(mItemTakeReservation);
		mReservation.add(mItemReservationList);
		//==========================END MENU Reservation===================/
		//Create Object Menu Item of Help
		mItemWelcome = new JMenuItem("Welcome");
		mItemHelpContents = new JMenuItem("Help Contents");		
		mItemCheckUpdate = new JMenuItem("Check Update");
		mItemAbout = new JMenuItem("About Restaurant Reservation");
		//Create Object Menu Help and add its items
		mHelp = new JMenu("Help");
		mHelp.add(mItemWelcome);
		mHelp.add(mItemHelpContents);
		mHelp.add(new JSeparator());
		mHelp.add(mItemCheckUpdate);
		mHelp.add(mItemAbout);
		//==========================END MENU HELP======================/
		//Create object menu bar and add its menus
		menuBar = new JMenuBar();
		menuBar.add(mFile); //Add File
		menuBar.add(mTable); //Add Table
		menuBar.add(mFood); //Add Food
		menuBar.add(mOrder); //Add Order
		menuBar.add(mReservation); //Add Reservation
		menuBar.add(mHelp); // Add Help
		//=========================END MENU BAR======================/

		//Left Section as Tree
		JTree leftJTree = createJTree();
		//Right Section as Tab Panel
		jTab = new JTabbedPane();
		
		JSplitPane jsp = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, true, leftJTree, jTab);
		jsp.setDividerLocation(180);
		add(menuBar);
		add(jsp);
		setTitle("Restaurant Reservation Management");
		setJMenuBar(menuBar);
		setExtendedState(MAXIMIZED_BOTH);
		setVisible(true);
		
	}
// Start Block Reservation ==================================================================================
		private JPanel openReservationSection_NewReservation(JPanel newReservation) {
			
			TitledBorder tBorderNewTable = BorderFactory.createTitledBorder("CREATE NEW RESERVATION");
			tBorderNewTable.setTitleJustification(TitledBorder.CENTER);
			newReservation.setBorder(tBorderNewTable);
			
			JPanel head = new JPanel(new GridLayout(2,1, 20,20));
			head.add(new JLabel("Enter Reservation Information", SwingConstants.CENTER));
			head.add(new JSeparator());
			
			JPanel body = new JPanel(new GridLayout(5,2,10,10));
			
			body.add(new JLabel("Reservation ID :"));
			body.add(txtReservationID = new JTextField(12));
			body.add(new JLabel("Customer Name :"));
			body.add(txtCustomerName = new JTextField(12));
			body.add(new JLabel("Phone Number :"));
			body.add(txtCustomerNumber = new JTextField(12));
			body.add(new JLabel("Total Table Reserve :"));
			body.add(txtReservationTableNumber = new JTextField(12));
			body.add(new JLabel("Table ID :"));

			String[] list = {"001", "002","003","004"};
			// Get table from database
			SessionFactory factoryT =  new Configuration()
					.configure("hibernate.cfg.xml")
					.addAnnotatedClass(Tables.class)
					.buildSessionFactory();
			
			Session session = factoryT.getCurrentSession();
			
			List<String> listTableFree = new ArrayList<String>();
			
			try {
				session.beginTransaction();
				List<Tables> tableList = session.createQuery("from Tables").getResultList();
				session.getTransaction().commit();
				for (Tables table : tableList) {
					if(table.getOccupied()) {
						listTableFree.add(table.getID());
					}
				}
				System.out.println("Database contents delivered... Table retrieve into list reservation");
			}catch (Exception e) {
				e.printStackTrace();
			}finally {
				factoryT.close();
			}
			// convert list into string [] 
			String[] listTableFreeID = listTableFree.toArray(new String[listTableFree.size()]); 
			//=======================
			
			listTableIDFree = listTableFreeID;
			
			final JList<String> listA = new JList<String>(listTableFreeID);
			
			body.add(new JScrollPane(listA));
			
			listA.addListSelectionListener(new ListSelectionListener() {
				
				public void valueChanged(ListSelectionEvent e) {
			// get selected table id
					List<String> list = new ArrayList<String>(); 
					// TODO Auto-generated method stub	
					list = listA.getSelectedValuesList();
					getListTableReserved = list;
				System.out.println("set value of list table id");	
				}
			});;
			
			JPanel allB = new JPanel(new GridLayout(2,1,10,30));
			
			JPanel btnAction = new JPanel(new FlowLayout());
			btnAction.add(btnReservationDelete = new JButton("Delete"));
			btnAction.add(btnReservationUpdate = new JButton("Update"));
			btnReservationUpdate.addActionListener(this);
			btnAction.add(btnReservationSave = new JButton("Save"));
			btnReservationSave.addActionListener(this);
			btnAction.add(btnReservationClear = new JButton("CANCEL"));
			btnReservationClear.addActionListener(this);
			allB.add(body);
			allB.add(btnAction);
			
			JPanel newReservationPanel = new JPanel(new BorderLayout(10, 10));
			newReservationPanel.add(head, BorderLayout.NORTH);
			newReservationPanel.add(allB, BorderLayout.CENTER);
			
			JPanel NewFoodFinal = new JPanel(new CardLayout(10,30));
			
			NewFoodFinal.add(newReservationPanel);
			return NewFoodFinal;
		}
		private void openReservationSection() {
			JPanel reservationPanel = new JPanel(new GridLayout(1,2));
			JPanel reservationList = new JPanel(new CardLayout(10,50));
			JPanel reservationNew = new JPanel(new CardLayout(10,50));

			reservationNew.add(openReservationSection_NewReservation(reservationNew));
			reservationList.add(openReservationSection_ListReservation(reservationList));
			
			reservationPanel.add(reservationList);
			reservationPanel.add(reservationNew);
			JPanel table = new JPanel(new CardLayout(10,10));
			table.add(reservationPanel);
			jTab.addTab("Reservation Information", table);
			jTab.setSelectedComponent(table);
		}	
		
		private JPanel openReservationSection_ListReservation(JPanel reservationList) {
			
			TitledBorder tBorderListEmp = BorderFactory.createTitledBorder("LIST OF RESERVATION");
			tBorderListEmp.setTitleJustification(TitledBorder.CENTER);
			reservationList.setBorder(tBorderListEmp);
			
			JPanel head = new JPanel(new GridLayout(2,1, 20,20));
			head.add(new JLabel(" List of Reservation", SwingConstants.CENTER));
			head.add(new JSeparator());
			
			JPanel he = new JPanel(new FlowLayout(FlowLayout.LEFT));
			he.add(txtSearchReservation = new JTextField(12));
//			txtSearchReservation.setBounds(width, height);
			he.add(btnSearchReservation = new JButton("Search"));
			
			
			JPanel body = new JPanel(new CardLayout(10,10));
			
			tbReservationModel = new DefaultTableModel();
			tbReservationModel.addColumn("Reservation ID");
			tbReservationModel.addColumn("Customer Name");
			tbReservationModel.addColumn("Contact Number");
			tbReservationModel.addColumn("Table Number");

			tbReservation = new JTable(tbReservationModel);
			body.add(new JScrollPane(tbReservation));
			
			JPanel allB = new JPanel(new BorderLayout(10,10));
			
			allB.add(he, BorderLayout.NORTH);
			allB.add(body, BorderLayout.CENTER);
			
			JPanel ListReservation = new JPanel(new BorderLayout(10,30));
			ListReservation.add(head, BorderLayout.NORTH);
			ListReservation.add(allB, BorderLayout.CENTER);
			
			JPanel ListReservationFinal = new JPanel(new CardLayout(10,30));
			ListReservationFinal.add(ListReservation);
			
			return ListReservationFinal;
		}
// Finished Block Reservation =========================================================================
		// Start Block Order ==================================================================================
		private JPanel openOrderSection_NewOrder(JPanel newOrder) {
			
			TitledBorder tBorderNewTable = BorderFactory.createTitledBorder("CREATE NEW ORDER");
			tBorderNewTable.setTitleJustification(TitledBorder.CENTER);
			newOrder.setBorder(tBorderNewTable);
			
			JPanel head = new JPanel(new GridLayout(2,1, 20,20));
			head.add(new JLabel("Enter Order Information", SwingConstants.CENTER));
			head.add(new JSeparator());
			
			JPanel allB = new JPanel(new GridLayout(2,1,10,30));
			
			JPanel body = new JPanel(new GridLayout(3,2,20,20));
			body.add(new JLabel("Order ID :"));
			body.add(txtOrderID = new JTextField(12));
			body.add(new JLabel("Table ID:"));
			body.add(jbcOrderTableID = new JComboBox());
//			jbcOrderTableID.remove(0);
			
			
			body.add(new JLabel("Food ID:"));
			body.add(jbcOrderFoodID = new JComboBox());
			
			JPanel btnAction = new JPanel(new FlowLayout());
			
			btnAction.add(btnOrderDelete = new JButton("Delete"));
			btnOrderDelete.addActionListener(this);
			btnAction.add(btnOrderUpdate = new JButton("Update"));
			btnOrderDelete.addActionListener(this);
			btnAction.add(btnOrderSave = new JButton("ORDER"));
			btnOrderSave.addActionListener(this);
			btnAction.add(btnOrderClear = new JButton("CANCEL"));
			btnOrderClear.addActionListener(this);
			
			allB.add(body);
			allB.add(btnAction);
			
			JPanel newOrderPanel = new JPanel(new BorderLayout(50, 100));
			newOrderPanel.add(head, BorderLayout.NORTH);
			newOrderPanel.add(allB, BorderLayout.CENTER);
			
			JPanel NewOrderFinal = new JPanel(new CardLayout(10,30));
			
			NewOrderFinal.add(newOrderPanel);
			return NewOrderFinal;
		}
		private void openOrderSection() {
			JPanel orderPanel = new JPanel(new GridLayout(1,2));
			JPanel orderList = new JPanel(new CardLayout(10,50));
			JPanel orderNew = new JPanel(new CardLayout(10,50));

			orderNew.add(openOrderSection_NewOrder(orderNew));
			orderList.add(openOrderSection_ListOrder(orderList));
			
			orderPanel.add(orderList);
			orderPanel.add(orderNew);
			JPanel table = new JPanel(new CardLayout(10,10));
			table.add(orderPanel);
			jTab.addTab("Order Information", table);
			jTab.setSelectedComponent(table);
		}	
		
		private JPanel openOrderSection_ListOrder(JPanel orderList) {
			
			TitledBorder tBorderListEmp = BorderFactory.createTitledBorder("LIST OF ORDER");
			tBorderListEmp.setTitleJustification(TitledBorder.CENTER);
			orderList.setBorder(tBorderListEmp);
			
			JPanel head = new JPanel(new GridLayout(2,1, 20,20));
			head.add(new JLabel(" List of Order", SwingConstants.CENTER));
			head.add(new JSeparator());
			
			JPanel search = new JPanel(new FlowLayout(FlowLayout.LEFT));
			
			search.add(txtSearchOrder = new JTextField(12));
			search.add(btnSearchOrder = new JButton("search"));
			
			JPanel body = new JPanel(new CardLayout(10,10));
			
			tbOrderModel = new DefaultTableModel();
			tbOrderModel.addColumn("Order ID");
			tbOrderModel.addColumn("Table ID");
			tbOrderModel.addColumn("Food ID");
			
			tbOrder = new JTable(tbOrderModel);
			body.add(new JScrollPane(tbOrder));
			
			JPanel allB = new JPanel(new BorderLayout(10,10));
			
			allB.add(search, BorderLayout.NORTH);
			allB.add(body, BorderLayout.CENTER);
			
			JPanel ListOrder = new JPanel(new BorderLayout(10,30));
			
			ListOrder.add(head, BorderLayout.NORTH);
			ListOrder.add(allB, BorderLayout.CENTER);
			
			JPanel ListOrderFinal = new JPanel(new CardLayout(10,30));
			ListOrderFinal.add(ListOrder);
			
			return ListOrderFinal;
		}
	// Finished Block Order ================================================================================
	// Food Block ===================================================================================================
		private JPanel openFoodSection_NewFood(JPanel newFood) {
			
			TitledBorder tBorderNewTable = BorderFactory.createTitledBorder("CREATE NEW FOOD");
			tBorderNewTable.setTitleJustification(TitledBorder.CENTER);
			newFood.setBorder(tBorderNewTable);
			
			JPanel head = new JPanel(new GridLayout(2,1, 20,20));
			head.add(new JLabel("Enter Food Information", SwingConstants.CENTER));
			head.add(new JSeparator());
			JPanel body = new JPanel(new GridLayout(3,2,20,15));
			body.add(new JLabel("Food ID :"));
			body.add(txtFoodID = new JTextField(12));
			body.add(new JLabel("Food Name:"));
			body.add(txtFoodName = new JTextField(12));
			body.add(new JLabel("Food Price ($):"));
			body.add(txtFoodPrice = new JTextField(12));
			JPanel btnAction = new JPanel(new FlowLayout());
			btnAction.add(btnFoodDelete = new JButton("Delete"));
			btnFoodDelete.addActionListener(this);
			btnAction.add(btnFoodUpdate = new JButton("Update"));
			btnFoodUpdate.addActionListener(this);
			btnAction.add(btnFoodSave = new JButton("SAVE"));
			btnFoodSave.addActionListener(this);
			btnAction.add(btnFoodClear = new JButton("CANCEL"));
			btnFoodClear.addActionListener(this);

			JPanel Allbody = new JPanel(new GridLayout(2, 1, 10, 50));
			
			Allbody.add(body);
			Allbody.add(btnAction);
			
			JPanel newFoodPanel = new JPanel(new BorderLayout(50, 100));
			newFoodPanel.add(head, BorderLayout.NORTH);
			newFoodPanel.add(Allbody, BorderLayout.CENTER);
			
			JPanel NewFoodFinal = new JPanel(new CardLayout(10,30));
			
			NewFoodFinal.add(newFoodPanel);
			return NewFoodFinal;
		}
		private void openFoodSection() {
			JPanel foodPanel = new JPanel(new GridLayout(1,2));
			JPanel foodList = new JPanel(new CardLayout(10,50));
			JPanel foodNew = new JPanel(new CardLayout(10,50));

			foodNew.add(openFoodSection_NewFood(foodNew));
			foodList.add(openFoodSection_ListFood(foodList));
			
			foodPanel.add(foodList);
			foodPanel.add(foodNew);
			JPanel table = new JPanel(new CardLayout(10,10));
			table.add(foodPanel);
			jTab.addTab("Foods Information", table);
			jTab.setSelectedComponent(table);
		}	
		
		private JPanel openFoodSection_ListFood(JPanel foodList) {
			
			TitledBorder tBorderListEmp = BorderFactory.createTitledBorder("LIST OF FOOD");
			tBorderListEmp.setTitleJustification(TitledBorder.CENTER);
			foodList.setBorder(tBorderListEmp);
			
			JPanel head = new JPanel(new GridLayout(2,1, 20,30));
			head.add(new JLabel(" List of Foods"));
			head.add(new JSeparator());
			
			JPanel allB = new JPanel(new BorderLayout(10,10));

			JPanel sear = new JPanel(new FlowLayout(FlowLayout.LEFT));
			sear.add(txtSearchFood = new JTextField(12));
			txtSearchFood.addActionListener(this);
			sear.add(btnSearchFood = new JButton("search"));
			btnSearchFood.addActionListener(this);
			JPanel body = new JPanel(new CardLayout(10,10));

			tbFoodModel = new DefaultTableModel();
			tbFoodModel.addColumn("Food ID");
			tbFoodModel.addColumn("Food Name");
			tbFoodModel.addColumn("Food Price($)");
			String row[] = {"001", "Shokiyaki", "50.00"};
//			tbFood = new JTable(row,col);
			
			for (int i = 0; i < 50; i++) {
				tbFoodModel.addRow(row);
			}
			
			tbFood = new JTable(tbFoodModel);
			body.add(new JScrollPane(tbFood));
			allB.add(sear, BorderLayout.NORTH);
			allB.add(body, BorderLayout.CENTER);
//			-----------------------------------EDIT TABLE FROM------------------------------------
			
				tbFood.addMouseListener(new MouseAdapter() {
					
					  public void mouseClicked(java.awt.event.MouseEvent evt) {
					    	tbFoodModel = (DefaultTableModel) tbFood.getModel();
					        if(evt.getClickCount() == 1) {	
					        	int index = tbFood.getSelectedRow();
					        	txtFoodID.setText(tbFoodModel.getValueAt(index,0).toString());
					    		txtFoodName.setText(tbFoodModel.getValueAt(index,1).toString());	
					    		txtFoodPrice.setText(tbFoodModel.getValueAt(index,2).toString());	
					        }
					      
					    }
					 });
				
			JPanel ListFood = new JPanel(new BorderLayout(10,30));
			ListFood.add(head, BorderLayout.NORTH);
			ListFood.add(allB, BorderLayout.CENTER);
			
			JPanel ListFoodFinal = new JPanel(new CardLayout(10,30));
			ListFoodFinal.add(ListFood);
			
			return ListFoodFinal;
		}
	// Finished Food Block ========================================================================================== 
	// Table block ===================================================================================================
		private JPanel openTableSection_NewTable(JPanel tableNew) {
			// Create Group Box - New Employee
			TitledBorder tBorderNewTable = BorderFactory.createTitledBorder("CREATE NEW TABLE");
			tBorderNewTable.setTitleJustification(TitledBorder.CENTER);
			tableNew.setBorder(tBorderNewTable);
			//Create Block All Info
			
			JPanel head = new JPanel(new GridLayout(2,1, 10,20));
			head.add(new JLabel(" List of Foods"));
			head.add(new JSeparator());
			
			JPanel allB = new JPanel (new GridLayout(2,1, 20,30));
			
			JPanel blockTableInfo = new JPanel(new GridLayout(4,2,10,15));
			
			blockTableInfo.add(new JLabel(""));
			blockTableInfo.add(new JLabel(""));
			blockTableInfo.add(new JLabel("Table ID :"));
			blockTableInfo.add(txtTableID = new JTextField(12));
			blockTableInfo.add(new JLabel("Total Seats:"));
			blockTableInfo.add(txtTableSeats = new JTextField(12));
			blockTableInfo.add(new JLabel(""));
			blockTableInfo.add(new JLabel(""));
			
			JPanel actionBtnPanel = new JPanel(new FlowLayout());
			actionBtnPanel.add(btnTableDelete = new JButton("Delete"));
			btnTableDelete.addActionListener(this);
			actionBtnPanel.add(btnTableUpdate = new JButton("Update"));
			btnTableUpdate.addActionListener(this);
			actionBtnPanel.add(btnTableSave = new JButton("SAVE"));
			btnTableSave.addActionListener(this);
//			=================================================================================update======================================
			actionBtnPanel.add(btnTableClear = new JButton("CLEAR"));
			btnTableClear.addActionListener(this);
			// Create Block Employee Info - FINAL
			allB.add(blockTableInfo);
			allB.add(actionBtnPanel);
			
			JPanel blockAllInfo = new JPanel(new BorderLayout(10,10));
			blockAllInfo.add(head, BorderLayout.NORTH);
			blockAllInfo.add(allB, BorderLayout.CENTER);
			//=============================================
			//=============================================
			return blockAllInfo;
		}
		private JPanel openTableSection_ListTable(JPanel tableList) {
			// TODO Auto-generated method stub
			TitledBorder tBorderListTable = BorderFactory.createTitledBorder("LIST OF TABLE");
			tBorderListTable.setTitleJustification(TitledBorder.CENTER);
			tableList.setBorder(tBorderListTable);
			
			JPanel head = new JPanel(new GridLayout(2,1, 20,20));
			head.add(new JLabel(" List of Table"));
			head.add(new JSeparator());
			
			JPanel body = new JPanel(new BorderLayout(10,10));
			
			JPanel sa = new JPanel(new FlowLayout(FlowLayout.LEFT));
			sa.add(txtSearchTable = new JTextField(12));
			sa.add(btnSearchTable = new JButton("search"));
			btnSearchTable.addActionListener(this);
			body.add(sa, BorderLayout.NORTH);
			
			tbModel = new DefaultTableModel();
			tbModel.addColumn("Table ID");
			tbModel.addColumn("Table Seats");
			tbModel.addColumn("Table Available");		
			tbListTable = new JTable(tbModel);
			body.add(new JScrollPane(tbListTable), BorderLayout.CENTER);

//			-----------------------------------EDIT TABLE FROM------------------------------------	
			tbListTable.addMouseListener(new MouseAdapter() {
					
					  public void mouseClicked(java.awt.event.MouseEvent evt) {
					    	tbModel = (DefaultTableModel) tbListTable.getModel();
					        if(evt.getClickCount() == 1) {	
					        	int index = tbListTable.getSelectedRow();
					        	txtTableID.setText(tbModel.getValueAt(index,0).toString());
					        	txtTableSeats.setText(tbModel.getValueAt(index,1).toString());	        	
					        }
					      
					    }
			});
		

			JPanel ListTable = new JPanel(new BorderLayout(10,30));
			ListTable.add(head, BorderLayout.NORTH);
			ListTable.add(body, BorderLayout.CENTER);
			
			JPanel ListTableFinal = new JPanel(new CardLayout(10,30));
			ListTableFinal.add(ListTable);
			
			return ListTableFinal;
		}
		
		private void openTableSection() {
			JPanel tablePanel = new JPanel(new GridLayout(1,2));
			JPanel tableList = new JPanel(new BorderLayout(10,10));
			JPanel tableNew = new JPanel(new CardLayout(10,50));

			tableNew.add(openTableSection_NewTable(tableNew));
			tableList.add(openTableSection_ListTable(tableList), BorderLayout.CENTER);
//			tableList.add(openTableSection_ListTable(tableList));
			
			tablePanel.add(tableList);
			tablePanel.add(tableNew);
			JPanel table = new JPanel(new CardLayout(10,10));
			table.add(tablePanel);
			jTab.addTab("Table Information", table);
			jTab.setSelectedComponent(table);
		}
		//=============================================================================================================
	// JTReeeeeee
	private JTree createJTree() {
		//JPanel leftPanel = new JPanel();
		// Create Tree Root Node
		DefaultMutableTreeNode rootNode = new DefaultMutableTreeNode("Root Node");
		// Create Tree Node Table Mgt
		DefaultMutableTreeNode nodeTable = new DefaultMutableTreeNode("Table Info");
		// Create Tree Node Add Employee
		DefaultMutableTreeNode nodeNewTable = new DefaultMutableTreeNode("New Table");
		// Create Tree Node List Employee
		DefaultMutableTreeNode nodeListTable = new DefaultMutableTreeNode("List Table");
		nodeTable.add(nodeNewTable);
		nodeTable.add(nodeListTable);
		// ============= End of Table Section ===============================//
		
		// Create Tree Node Food
		DefaultMutableTreeNode nodeFood = new DefaultMutableTreeNode("Food Info");
		// Create Tree Node New Food
		DefaultMutableTreeNode nodeNewFood = new DefaultMutableTreeNode("New Food");
		// Create Tree Node List Food
		DefaultMutableTreeNode nodeListFood = new DefaultMutableTreeNode("List Food");
		
		//================================END TREE NODE of Food Info=========//
		nodeFood.add(nodeNewFood);
		nodeFood.add(nodeListFood);
		
		// Create Tree Node Order
		DefaultMutableTreeNode nodeOrder = new DefaultMutableTreeNode("Order Info");
		// Create Tree Node New Food
		DefaultMutableTreeNode nodeNewOrder = new DefaultMutableTreeNode("Take Order");
		// Create Tree Node List Food
		DefaultMutableTreeNode nodeListOrder = new DefaultMutableTreeNode("List Order");
		nodeOrder.add(nodeNewOrder);
		nodeOrder.add(nodeListOrder);
		
		//================================ END TREE NODE of Order =========//
		
		// Create Tree Node Reservation
		DefaultMutableTreeNode nodeReservation = new DefaultMutableTreeNode("Reservation");
		// Create Tree Node Take Reservation
		DefaultMutableTreeNode nodeTakeReservation = new DefaultMutableTreeNode("Take Reservation");
		// Create Tree Node List Reservation
		DefaultMutableTreeNode nodeReservationList = new DefaultMutableTreeNode("List Reservation");
		nodeReservation.add(nodeTakeReservation);
		nodeReservation.add(nodeReservationList);
		//================================ END TREE NODE of Reservation =========//
		
		rootNode.add(nodeTable);
		rootNode.add(nodeFood);
		rootNode.add(nodeOrder);
		rootNode.add(nodeReservation);
		
		// Create object of JTree Restaurant Management
		jTreeRestaurant = new JTree(rootNode);
		jTreeRestaurant.setRowHeight(25);
		jTreeRestaurant.setRootVisible(false);
		jTreeRestaurant.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		// Add mouse listener to tree
		jTreeRestaurant.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				// Find selected node of tree
				int selectedNode = jTreeRestaurant.getRowForLocation(e.getX(), e.getY());
				// Condition when mouse pressed on a specific node
				if(selectedNode != -1) {
					// When mouse pressed is double clicked
					if(e.getClickCount() == 2) {
						//Get for whole tree path
						TreePath treePath = jTreeRestaurant.getPathForLocation(e.getX(), e.getY());
						//Get last selected tree path
						String lastSelectedPath = treePath.getLastPathComponent().toString();
						if(lastSelectedPath.equals("New Table")) {
							openTableSection();
							retrieveTable();
						}else if (lastSelectedPath.equals("List Table")) {
							openTableSection();
							retrieveTable();
						}else if (lastSelectedPath.equals("New Food")) {
							openFoodSection();
							retrieveFood();
						}else if (lastSelectedPath.equals("List Food")) {
							openFoodSection();
							retrieveFood();
						}else if (lastSelectedPath.equals("Take Order")) {
							openOrderSection();
						}else if (lastSelectedPath.equals("List Order")) {
							openOrderSection();
						}else if (lastSelectedPath.equals("Take Reservation")) {
							openReservationSection();
//							retrieveTables();
//							createReservation_save();
//							createBookingDetail();
//							clearReservation_fields();
							retrieveReservation();
						}else if (lastSelectedPath.equals("List Reservation")) {
							openReservationSection();
//							retrieveTables();
//							createReservation_save();
//							createBookingDetail();
//							clearReservation_fields();
							retrieveReservation();
						}
						
					}
				}
			}
		});
		// Expand all tree nodes
		for(int i =0; i<=jTreeRestaurant.getRowCount(); i++)
			jTreeRestaurant.expandRow(i);
		// Add tree to panel
		//leftPanel.add(jTreeEmp);
		return jTreeRestaurant;
	}
	// ActionPerformed for JMENUBar
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == mItemNewTable) {
			openTableSection();
			retrieveTable();
		}else if (e.getSource() == mItemListTable) {
			openTableSection();
			retrieveTable();
		}else if (e.getSource() == mItemNewFood) {
			openFoodSection();
			retrieveFood();
		}else if(e.getSource() == mItemListFood) {
			openFoodSection();
			retrieveFood();
		}else if(e.getSource() == mItemTakeOrder) {
			openOrderSection();
//			openOrderSection();
	//		getIdTable(jbcOrderTableID);
		//	getIdFood(jbcOrderFoodID);

		}else if(e.getSource() == mItemListOrder) {
			openOrderSection();
		}else if(e.getSource() == mItemTakeReservation) {
			
//			retrieveTables();
			openReservationSection();
			
//			retrieveTables();
//			retrieveReservation();
		}else if(e.getSource() == mItemReservationList) {
			openReservationSection();
			retrieveReservation();
//			retrieveReservation();
		}
		//===========================================
		else if(e.getSource() == btnFoodSave) {
				createFood_save();
				clearFood_fields();
				retrieveFood();
		}else if(e.getSource() == btnFoodClear) {
				clearFood_fields();
		}else if(e.getSource() == btnFoodUpdate) {
				updateFood();	
				retrieveFood();
				
		}else if(e.getSource() == btnTableUpdate) {
				updateTable();
				retrieveTable();
		}else if(e.getSource() == btnFoodDelete) {
			deleteFood();
			
		}else if(e.getSource() == btnSearchFood) {
			searchFoodID();
		}else if(e.getSource() == btnTableSave){
				createTable_save();
				clearTable_fields();
		}else if(e.getSource() == btnTableClear) {
				clearTable_fields();
		//===========================================
				
		}else if(e.getSource() == btnSearchTable) {
			searchTable();
		
		}else if(e.getSource() == btnTableDelete) {
			deleteTable();
		}else if(e.getSource() == btnReservationSave) {
//			retrieveTables();
			createReservation_save();
			clearReservation_fields();
			
//			retrieveReservation();
		}
	}
	
	
//	-----------------------------------CREATE TABLE WITH FROM------------------------------------

	public void litDataFromTabel() {
		SessionFactory factoryStudent =  new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Tables.class)
				.buildSessionFactory();
		
		Session session = factoryStudent.getCurrentSession();
		
		try {
			
			session.beginTransaction();
			Query resultTable= session.createQuery("from Tables");
			
			List<Tables> tableList  = resultTable.getResultList();

			for (Tables table : tableList) {
				tbModel.addRow(table.toStringData());
			}
			System.out.println("Database contents delivered...");
		} catch (Exception e) {
			e.printStackTrace();
		}
			
	}
//	=============================================TAKE ID DATA FROM TABLES TO SHOW IN JCOMBOBOX ====================================================================	
		 
		 public void  getIdTable(JComboBox IDTable){
				SessionFactory factoryStudent =  new Configuration()
						.configure("hibernate.cfg.xml")
						.addAnnotatedClass(Tables.class)
						.buildSessionFactory();
				Session session = factoryStudent.getCurrentSession();
				try {
					
					session.beginTransaction();
					Query queryResult = session.createQuery("from Tables");
					
					List<Tables> tableList  = queryResult.getResultList();
					for(Tables table:tableList) {
						IDTable.addItem(table.getID());
					}
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			
		 }
		
//		=============================================================================================================
		 
//			=============================================TAKE ID DATA FROM TABLES TO SHOW IN JCOMBOBOX ====================================================================	
		 
				 public void  getIdFood(JComboBox idFood){
						SessionFactory factoryStudent =  new Configuration()
								.configure("hibernate.cfg.xml")
								.addAnnotatedClass(Food.class)
								.buildSessionFactory();
						Session session = factoryStudent.getCurrentSession();
						try {
							
							session.beginTransaction();
							Query queryResult = session.createQuery("from Food");
							
							List<Food> foodList  = queryResult.getResultList();
							for(Food food:foodList) {
								idFood.addItem(food.getFoodID());
							}
							
						} catch (Exception e) {
							e.printStackTrace();
						}
						
					
				 }
				
//				=============================================================================================================
	
//	-----------------------------------CREATE TABLE WITH FROM------------------------------------
	public void createTable_save() {

		String tableId =txtTableID.getText();
		int tableNumSeat=Integer.parseInt(txtTableSeats.getText());
		
		SessionFactory factoryStudent =  new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Tables.class)
				.buildSessionFactory();
		Session session = factoryStudent.getCurrentSession();
		try {
			Tables tableObject = new Tables( tableNumSeat,tableId, true);
			session.beginTransaction();
			session.save(tableObject);
			
			session.getTransaction().commit();
			
			tbModel = (DefaultTableModel) tbListTable.getModel();
			
			tbModel.addRow(tableObject.toStringData());
			
			System.out.println("Inserted!!!!");
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			factoryStudent.close();
		}
		
	}
	
	
		
//	-----------------------------------RETRIEVE TABLE WITH FROM------------------------------------
	public void retrieveTable() {
		SessionFactory factoryStudent =  new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Tables.class)
				.buildSessionFactory();
		
		Session session = factoryStudent.getCurrentSession();
		
		tbModel = (DefaultTableModel) tbListTable.getModel();
		while(tbModel.getRowCount()>0)
			tbModel.removeRow(0);
		try {
			session.beginTransaction();
			Query resultTable= session.createQuery("from Tables");
			
			List<Tables> tableList  = resultTable.getResultList();

			for (Tables table : tableList) {
				tbModel.addRow(table.toStringData());
			}
			System.out.println("Database contents delivered...");
		} catch (Exception e) {
			e.printStackTrace();
		}
			
		
	}
//	-----------------------------------SEARCH TABLE WITH ID------------------------------------
	public void searchTable() {
		SessionFactory factoryStudent =  new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Tables.class)
				.buildSessionFactory();
		
		Session session = factoryStudent.getCurrentSession();
		
		tbModel = (DefaultTableModel) tbListTable.getModel();
		while(tbModel.getRowCount()>0)
			tbModel.removeRow(0);
		String idSearch =txtSearchTable.getText();
		try {
			session.beginTransaction();
			Query resultTable= session.createQuery("from Tables WHERE id LIKE'%"+idSearch+"%'");
			
			List<Tables> tableList  = resultTable.getResultList();

			for (Tables table : tableList) {
				tbModel.addRow(table.toStringData());
			}
			System.out.println("Database contents delivered...");
		} catch (Exception e) {
			e.printStackTrace();
		}
			
		
	}
//	UPDATE DATA FROM TABLE FORM WHERE ID
	public void updateTable() {
		JOptionPane.showMessageDialog(null, "Do you want to update this Food?");
		SessionFactory factoryStudent =  new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Tables.class)
				.buildSessionFactory();
		Session session = factoryStudent.getCurrentSession();
		try {

			String tableId =txtTableID.getText();
			String tableNumSeat =txtTableSeats.getText();
			
			session.beginTransaction();
			session.createQuery("update Tables set numSeats = "+ tableNumSeat +"where id ='"+tableId +"'").executeUpdate();
			
			
			session.getTransaction().commit();
			System.out.println("Updated!!!!");
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			factoryStudent.close();
		}
		retrieveTable();
	}
//	DELETE DATA FROM TABLE FORM WHERE ID
	public void deleteTable() {
		JOptionPane.showMessageDialog(null, "Do you want to Delete this Food?");
		SessionFactory factoryStudent =  new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Tables.class)
				.buildSessionFactory();
		Session session = factoryStudent.getCurrentSession();
		tbModel = (DefaultTableModel) tbListTable.getModel();
		while(tbModel.getRowCount()>0)
			tbModel.removeRow(0);
		try {

			String tableId =txtTableID.getText();
			int Id =Integer.parseInt(txtTableID.getText());
			session.beginTransaction();
			session.createQuery("delete from Tables where id ='"+tableId +"'").executeUpdate();
	
			
			session.getTransaction().commit();
			tbModel.removeRow(Id);
			System.out.println("Deleted!!!!");
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			factoryStudent.close();
		}
		retrieveTable();
	}
//	-----------------------------------CANCEL TABLE WITH FROM------------------------------------
	public void clearTable_fields() {
		txtTableID.setText("");
		txtTableSeats.setText("");
	}

	
//	-----------------------------------CREATE FOOD WITH FROM------------------------------------
	public void createFood_save() {
		
		String foodId =txtFoodID.getText();
		String foodName=txtFoodName.getText();
		double foodPrice = Double.parseDouble(txtFoodPrice.getText());
		
		SessionFactory factoryStudent =  new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Food.class)
				.buildSessionFactory();
		Session session = factoryStudent.getCurrentSession();
		try {
			tbFoodModel = (DefaultTableModel) tbFood.getModel();
			Food food = new Food(foodId, foodName, foodPrice);
			session.beginTransaction();
			session.save(food);
			
			session.getTransaction().commit();
			tbFoodModel.addRow(food.toStringData());
			System.out.println("Inserted!!!!");
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			
			factoryStudent.close();
		}
		
			
	}
//	-----------------------------------SEARCH FOOD WITH FROM------------------------------------
	public void searchFoodID() {
		SessionFactory factoryStudent =  new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Food.class)
				.buildSessionFactory();
		Session session = factoryStudent.getCurrentSession();
		tbFoodModel = (DefaultTableModel) tbFood.getModel();
		while(tbFoodModel.getRowCount()>0)
			tbFoodModel.removeRow(0);
		String id = txtSearchFood.getText().toString();
		try {
			
			session.beginTransaction();
			Query queryResult = session.createQuery("from Food WHERE id LIKE'%"+id+"%'");
			
			List<Food> foodList  = queryResult.getResultList();

			for (Food food : foodList) {
				tbFoodModel.addRow(food.toStringData());
			}
			System.out.println("Database contents delivered...");
		} catch (Exception e) {
			e.printStackTrace();
		}
			
		
	}
//	-----------------------------------RETRIEVE FOOD WITH FROM------------------------------------
	public void retrieveFood() {
		SessionFactory factoryStudent =  new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Food.class)
				.buildSessionFactory();
		Session session = factoryStudent.getCurrentSession();
		tbFoodModel = (DefaultTableModel) tbFood.getModel();
		while(tbFoodModel.getRowCount()>0)
			tbFoodModel.removeRow(0);
		try {
			
			session.beginTransaction();
			Query queryResult = session.createQuery("from Food");
			
			List<Food> foodList  = queryResult.getResultList();

			for (Food food : foodList) {
				tbFoodModel.addRow(food.toStringData());
			}
			System.out.println("Database contents delivered...");
		} catch (Exception e) {
			e.printStackTrace();
		}
			
		
	}
//	UPDATE DATA FOOD FORM WHERE ID
	public void updateFood() {
//		JOptionPane.showMessageDialog(null, "hello!!!");
		SessionFactory factoryStudent =  new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Food.class)
				.buildSessionFactory();
		Session session = factoryStudent.getCurrentSession();
		try {

			String foodId =txtFoodID.getText();
			String foodName=txtFoodName.getText();
			double foodPrice = Double.parseDouble(txtFoodPrice.getText());		
		
			session.beginTransaction();
			Food  food =  session.get(Food.class, foodId);
			System.out.println(food);
			food.setName(foodName);
			food.setPrice(foodPrice);
			
			//session.save(food);
			session.getTransaction().commit();
			
			JOptionPane.showMessageDialog(null, "Food Updated!");
			System.out.println("Food Updated!!!!");
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			factoryStudent.close();
		}
//		retrieveFood();
	}
//	Delete DATA FOOD FORM WHERE ID
	public void deleteFood() {
//		JOptionPane.showMessageDialog(null, "hello!!!");
		SessionFactory factoryStudent =  new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Food.class)
				.buildSessionFactory();
		Session session = factoryStudent.getCurrentSession();
		try {

			String foodId =txtFoodID.getText();
				
		
			session.beginTransaction();
			Food  food =  session.get(Food.class, foodId);
			
			session.delete(food);
			session.getTransaction().commit();
			
			JOptionPane.showMessageDialog(null, "Food Delete!");
			System.out.println("Food Deleted!!!!");
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			factoryStudent.close();
		}
//		retrieveFood();
	}
//	-----------------------------------CLEAR FOOD WITH FROM------------------------------------
	public void clearFood_fields() {
		txtFoodID.setText("");
		txtFoodName.setText("");
		txtFoodPrice.setText("");
	}
//=============================================	
	// ================================= Take Reservation ===================================
	public void createReservation_save() {
		
		int Id = Integer.parseInt(txtReservationID.getText());
		String customerName=txtCustomerName.getText();
		String customerPhone =txtCustomerNumber.getText();
		
//		String tableID = jcbReservationTableID.getText();
//		double table = Double.parseDouble(txtFoodPrice.getText());
		
		SessionFactory factoryStudent =  new Configuration()
					.configure("hibernate.cfg.xml")
					.addAnnotatedClass(Booking.class)
					.buildSessionFactory();
		Session session = factoryStudent.getCurrentSession();
		
		SessionFactory factoryT =  new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Tables.class)
				.buildSessionFactory();
		Session sessionT = factoryT.getCurrentSession();
		
		SessionFactory factoryBooking =  new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(BookingDetail.class)
				.buildSessionFactory();
		Session sessionB = factoryBooking.getCurrentSession();
	
		String bookingId = String.valueOf(txtReservationID.getText());
	
		try {
			session.beginTransaction();
			sessionT.beginTransaction();
			sessionB.beginTransaction();		
			
			// Set status table to false 
			String[] listTID = getListTableReserved.toArray(new String[getListTableReserved.size()]);
			
			Query queryTables = sessionT.createQuery("from Tables");
			List<Tables> tableList  = queryTables.getResultList();
			for (Tables table : tableList) {
				for (String id : listTID) {
					if(id.equals(table.getID())){
						table.setOccupied(false);
					}
				}
			}
//			getListTableReserved
			
			String str = String.join(" #", listTID);
			Booking booking = new Booking(Id, customerName, customerPhone);
			String [] data = { booking.getBookingID()+" ", booking.getCustomerName(), booking.getContactNo(),"#"+str};
			tbReservationModel.addRow(data);
			tbReservation = new JTable(tbReservationModel);
//			booking.setReservedTable(null);	
			// Save booking detail
			for (String s : listTID) {
				
				BookingDetail bDetail = new BookingDetail(bookingId, "#"+ s);
				sessionB.save(bDetail);
			
			}	
			
			session.save(booking);	
			session.getTransaction().commit();
			sessionT.getTransaction().commit();
			sessionB.getTransaction().commit();
			
			System.out.println("Booking Detail has been Inserted!!!!");
			System.out.println("Booking has been Inserted!!!!");
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			factoryStudent.close();
			factoryT.close();
		}
	}
	public void clearReservation_fields() {
		txtReservationID.setText("");
		txtCustomerName.setText("");
		txtCustomerNumber.setText("");
		txtReservationTableNumber.setText("");
	}
	
	// ======== Finish block Reservation ===================
		
	public void retrieveReservation() {
		SessionFactory factoryReservation =  new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Booking.class)
				.buildSessionFactory();
		Session session = factoryReservation.getCurrentSession();
		
		SessionFactory factoryDetail =  new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(BookingDetail.class)
				.buildSessionFactory();
		Session sessionDetail = factoryDetail.getCurrentSession();
		

		tbReservationModel = (DefaultTableModel) tbReservation.getModel();
		while(tbReservationModel.getRowCount()>0)
			tbReservationModel.removeRow(0);
		try {
			
			session.beginTransaction();
			sessionDetail.beginTransaction();
			//			sessionT.beginTransaction();
//			List<Booking> bookingList = session.createQuery("SELECT * FROM booking b INNER JOIN bookingdetail bd on b.id = bd.bookingID").getResultList();
			
			List<Booking> bookingList = session.createQuery("from Booking").getResultList();
			
			List<BookingDetail> bDetailList = sessionDetail.createQuery("from BookingDetail").getResultList();

			//			Query queryBdetail = sessionT.createQuery("from Tables");
			
			// Get Data From Booking and Booking detail into Table Reservation
			
			List<String> tableID = new ArrayList<String>();
			
			for (Booking b : bookingList) {
				
				for (BookingDetail bD : bDetailList) {	
					String id = String.valueOf(b.getBookingID());
					if (id.equals(bD.getReservedbBookingID())) {
						tableID.add(bD.getReservedbTableID().toString());
					}
					this.ta = tableID.toArray(new String[tableID.size()]);
					this.str = String.join(" |", this.ta);

				}				
// this.str can not work please check.
				
				String[] tabl = { b.getBookingID() +"",b.getCustomerName(), b.getContactNo() , this.str};	
				
				tbReservationModel.addRow(tabl);
				
			}
			tbReservation = new JTable(tbReservationModel);
			session.getTransaction().commit();
			sessionDetail.getTransaction().commit();
//			sessionT.getTransaction().commit();
			
			System.out.println("Database contents delivered...Retrieve data from Reservation detail and booking");
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			factoryReservation.close();
			factoryDetail.close();
//			factoryT.close();
		}
			
		
	}
	//
	// ======================================================
    public static void main( String[] args )
    {
    	new MainRun();
    }
}
