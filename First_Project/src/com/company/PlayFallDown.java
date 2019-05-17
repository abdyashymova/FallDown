package com.company; import javax.swing.*;
import java.awt.*;

public class PlayFallDown extends JFrame
{
	public PlayFallDown()
	{
		getContentPane().setLayout(new FlowLayout());
		add(new FallDownComponent());
		pack();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}com

	public static void main(String[] args)
	{
		new PlayFallDown();
	}
}
