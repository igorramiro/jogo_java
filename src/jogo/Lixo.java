package jogo;

import java.awt.Image;
import java.awt.Rectangle;

import java.util.Random;

import javax.swing.ImageIcon;

public class Lixo {
	private Image imagem;
	private int x,y;
	private int altura,largura;
	private boolean isVisivel=false;
	private String[] imagens = 
	{"res\\lixo\\lixo metal.png", 
	"res\\lixo\\lixo papel.png", 
	"res\\lixo\\lixo vidro.png",
	"res\\lixo\\lixo plastico.png"};
	private int tipo;
	private static int VELOCIDADE=2;//ele vai no sentido oposto do  boneco

	public Lixo(int x,int y) {
		Random gerador = new Random();
		// TODO Auto-generated constructor stub
		this.x=x;
		this.y=y;
		
		this.tipo=gerador.nextInt(4);
		
		this.isVisivel=true;
	}
	public void carregar_imagem() {
		ImageIcon ref=new ImageIcon(imagens[tipo]);
		imagem=ref.getImage();
		
		altura=imagem.getHeight(null);
		largura=imagem.getWidth(null);
	}
	public void atualiza_imagem() {
		this.y += VELOCIDADE;//ele vai no sentido oposto do  boneco
		if(y>480 && isVisivel){//se não bateu na lixeira correta
			isVisivel=false;
		}
	}
	// Gets e Sets
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public int getTipo() {
		return tipo;
	}
	public int getlargura(){
		return largura;
	}
	public Image getImagem() {
		return imagem;
	}
	public Rectangle getBounds(){
		return new Rectangle(x,y,largura,altura);//x,y e tamanho da imagem
		
	}
	public boolean getisVisivel() {
		return isVisivel;
	}
	public void setVisivel(boolean isVisivel) {
		this.isVisivel = isVisivel;
	}
	public static int getVELOCIDADE() {
		return VELOCIDADE;
	}
	public static void setVELOCIDADE(int vELOCIDADE) {
		VELOCIDADE = vELOCIDADE;
	}

}
