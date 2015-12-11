//Vinzent Davies
//Java 256
//10.30.15
//Midterm



package midterm;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import java.beans.BeanInfo;
import java.beans.Beans;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class JFrameExt extends JFrame implements ActionListener {

	// shared variables
	private JPanel targetBeanObject = null;
	private Class classObject = null;
	private PropertyDescriptor[] pd = null;
	private JPanel contentPane;
	// Create an array of 10 JLabels that label the properties
	private JLabel[] jlbPropNames = new JLabel[10];

	// Create an array of 10 JTextFields so that we can change the properties
	private JTextField[] jtfPropValues = new JTextField[10];

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JFrameExt frame = new JFrameExt();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public JFrameExt() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(0, 2, 0, 0));

		JPanel jpLeft = new JPanel();
		jpLeft.setBackground(Color.YELLOW);
		contentPane.add(jpLeft);

		JPanel jpRight = new JPanel();
		contentPane.add(jpRight);
		jpRight.setLayout(new BorderLayout(0, 0));

		JPanel jpController = new JPanel();
		jpController.setBackground(Color.MAGENTA);
		jpRight.add(jpController, BorderLayout.NORTH);

		final JComboBox jcboClassName = new JComboBox();
		jcboClassName.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Get target bean class name from combo box
				String className = (String) jcboClassName.getSelectedItem();
				if (className.equals(""))
					return;

				// Create target bean object using bean class name.
				try {
					targetBeanObject = (JPanel) Beans.instantiate(null,
							className);
				} catch (ClassNotFoundException ex) {
				} catch (IOException ex) {
				}
				// If targetBeanObject is a JPanel,
				// remove jpLeft and replace it with targetBeanObject
				// and validate (refresh) the content pane.
				if (targetBeanObject instanceof JPanel) {
					contentPane.remove(0); // remove component at index 0
					contentPane.add(targetBeanObject, 0); // add at index 0

					// set the background color the target bean object to say
					// pink
					targetBeanObject.setBackground(Color.PINK);
					// validate the contentpane.
					contentPane.validate();
				}

				// Create Class object using the class name.
				// Create BeanInfo object using the Class object.
				BeanInfo bi = null;
				try {
					classObject = Class.forName(className);

					// The first parameter below indicates the class object to
					// be used for getting properties.
					// The second parameter indicates the parent Class object
					// before which to stop getting properties.
					// Below, getBeanInfo would get properties from classObject
					// class object but won’t get
					// properties from JPanel class object which is its parent
					// i.e. it would stop getting properties
					// at JPanel class object.
					// If the second parameter below was Object.class, it would
					// get properties from classObject
					// class object and also from JPanel class object (the
					// parent), but won’t get properties
					// from Object class object (the grand parent) i.e. it would
					// stop at Object class object.

					bi = Introspector.getBeanInfo(classObject, JPanel.class);
				} catch (ClassNotFoundException ex) {
				} catch (IntrospectionException ex) {
				}

				// Get an array of PropertyDescriptor objects from BeanInfo
				// object.
				pd = bi.getPropertyDescriptors();

				String propName;
				
				for (int i = 0; i < pd.length; i++) {
					// Get property name from corresponding PropertyDescriptor
					// array element.
					// Set property name in the corresponding JLabel array
					// element.
					propName = pd[i].getName();

					// Set propName as the text for the corresponding JLabel.
					jlbPropNames[i].setText(propName);

					Method mget = pd[i].getReadMethod();
					Object robj = null;
					try {
						robj = mget.invoke(targetBeanObject, null);
					} catch (IllegalAccessException ex) {
					} catch (IllegalArgumentException ex) {
					} catch (InvocationTargetException ex) {
					}
					// convert the received object contents to a String
					String sobj = robj.toString();

					// Set the String sobj as the text in the corresponding text
					// field.
					jtfPropValues[i].setText(sobj);
					
					//clean the data
					   for ( int j = pd.length; j < 10; j++){
						  jlbPropNames[j].setText(null);
						  jtfPropValues[j].setText(null);
						  }
					

				}
			}
		});
		jcboClassName.setEditable(true);
		jcboClassName.addItem("");
		jcboClassName.addItem("midterm.Rect");
		jcboClassName.addItem("midterm.Circ");
		jcboClassName.addItem("midterm.Ticker");

		jpController.add(jcboClassName);

		JPanel jpInspector = new JPanel();
		jpRight.add(jpInspector, BorderLayout.CENTER);
		jpInspector.setLayout(new GridLayout(0, 2, 0, 0));

		JPanel jpPropNames = new JPanel();
		jpPropNames.setBackground(Color.PINK);
		jpInspector.add(jpPropNames);
		jpPropNames.setLayout(new GridLayout(10, 1, 0, 0));

		JPanel jpPropValues = new JPanel();
		jpPropValues.setBackground(Color.PINK);
		jpInspector.add(jpPropValues);
		jpPropValues.setLayout(new GridLayout(10, 1, 0, 0));

		// Creating JLabel objects one by one in a loop
		for (int j = 0; j < 10; j++) {
			// Create JLabel object
			jlbPropNames[j] = new JLabel("");
			// Add JLabel object to JPanel
			jpPropNames.add(jlbPropNames[j]);
			
		}
		

		// Creating JTextField objects one by one in a loop
		for (int j = 0; j < 10; j++) {
			// Create JTextField objects. Each of width 10
			jtfPropValues[j] = new JTextField("");
			jtfPropValues[j].setColumns(10);
			// Register this as the listener with JTextField object
			jtfPropValues[j].addActionListener(this);

			// Add JTextField object to JPanel
			jpPropValues.add(jtfPropValues[j]);
		}

	}

	public void actionPerformed(ActionEvent e) {
		
		int i;
		String propName = "", propValue = "";

		// Determine the name, value and index of the property that changed.
		for (i = 0; i < jtfPropValues.length; i++) {
			if (e.getSource() == jtfPropValues[i]) {

				break;
			}
		}

		// Get the property name and the property value from the
		// JLabel and JTextfield corresponding to the i value.
		propValue = jtfPropValues[i].getText();
		propName = jlbPropNames[i].getName();

		// Note that the property index for the above property in the pd
		// array is the same. So you can go to that index in pd array and
		// access its property type .
		Class propType = pd[i].getPropertyType();
		// Get the property type as a String
		String propTypeName = propType.getName();

		// Create Object array for storing parameters
		Object[] params = new Object[1];

		// Depending upon property name, create correct parameter object.
		if (propTypeName.equals("int")) {
			params[0] = new Integer(Integer.parseInt(propValue));
		} else if (propTypeName.equals("double")) {
			params[0] = new Double(Double.parseDouble(propValue));
		} else if (propTypeName.equals("boolean")) {
			params[0] = new Boolean(propValue);
		} else if (propTypeName.equals("java.lang.String")) {
			params[0] = propValue;
		}
		// Get the set method object.
		Method mset = pd[i].getWriteMethod();
		// Invoke set method and pass it target bean and parameters.
		try {
			mset.invoke(targetBeanObject, params);
		} catch (IllegalAccessException ex) {
		} catch (IllegalArgumentException ex) {
		} catch (InvocationTargetException ex) {
		}
	}

}
