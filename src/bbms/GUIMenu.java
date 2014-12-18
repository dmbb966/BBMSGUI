/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package bbms;

import java.awt.event.*;

import javax.swing.*;

@SuppressWarnings("serial")
public class GUIMenu extends JMenuBar{
	
	public GUIMenu() {
		// File Menu		
		JMenu menu = new JMenu("File");
		menu.setMnemonic(KeyEvent.VK_F);			
		add(menu);
		
		JMenuItem menuItem = new JMenuItem("New", KeyEvent.VK_N);
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				System.out.println("File - New");
				GUI_NB.GCO("File - new");
			}
		});
		
		menu.add(menuItem);		
		menu.addSeparator();
		
		menuItem = new JMenuItem("Exit", KeyEvent.VK_X);
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.CTRL_MASK));
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				System.out.println("File - Exit");
				GUI_NB.GCO("File - Exit");
				System.exit(0);
			}
		});
		menu.add(menuItem);
		
		
		// Test Menu
		menu = new JMenu("Test");
		menu.setMnemonic(KeyEvent.VK_T);			
		add(menu);
		
		menuItem = new JMenuItem("Run Test 7 (Map Init)", KeyEvent.VK_7);		
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				System.out.println("Test - Test 7");
				GUI_NB.GCO("Test - Test 7");
				// FOVj.test7();
			}
		});
		menu.add(menuItem);
		
		
		// Help Menu
		menu = new JMenu("Help");
		menu.setMnemonic(KeyEvent.VK_H);			
		add(menu);
		
		menuItem = new JMenuItem("About", KeyEvent.VK_A);		
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				System.out.println("Bare Bones Military Simulator Experimental Version");
				GUI_NB.GCO("Bare Bones Military Simulator Experimental Version");
			}
		});
		menu.add(menuItem);
	}
}
