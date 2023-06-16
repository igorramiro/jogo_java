package jogo;

import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class Latas_lixo {
	public ImageIcon lata_metal=new ImageIcon("res\\latas\\lata metal.png");
	public ImageIcon lata_papel=new ImageIcon("res\\latas\\lata papel.png");
	public ImageIcon lata_vidro=new ImageIcon("res\\latas\\lata vidro.png");
	public ImageIcon lata_plastico=new ImageIcon("res\\latas\\lata plastico.png");

	private Rectangle[] caixa_colisão = {
		new Rectangle(0,550,lata_metal.getIconWidth(),lata_metal.getIconHeight()),
		new Rectangle(256,550,lata_papel.getIconWidth(),lata_papel.getIconHeight()), 
		new Rectangle(512,550,lata_plastico.getIconWidth(),lata_plastico.getIconHeight()),
		new Rectangle(768,550,lata_vidro.getIconWidth(),lata_vidro.getIconHeight())
	};
	
	public Rectangle getBounds(int tipo){
		return caixa_colisão[tipo];
	}
}