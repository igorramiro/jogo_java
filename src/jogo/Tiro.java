package jogo;

import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class Tiro {
	private Image imagem;
	private int x,y,tipo;
	private int altura,largura;
	private boolean isVisivel=false;
	
	private static final int LARGURA=0;
	private static int VELOCIDADE=2;
	
	public Tiro(int x, int y,int tipo) {//x e y do jogador
		this.x=x;
		this.y=y;
		this.tipo=tipo;
		this.isVisivel=true;
	}
	public void carrega_imagem() {
		ImageIcon ref=new ImageIcon("res\\tiro.png");
		imagem=ref.getImage();
		
		altura=imagem.getHeight(null);
		largura=imagem.getWidth(null);
	}
	public void atualiza_imagem() {
		this.y -= VELOCIDADE;
		if(this.y<LARGURA) {
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
	public Image getImagem() {
		return imagem;
	}
	public int getTipo() {
		return tipo;
	}
	//cria um quadrado em volta da imagem, quando o quadrado desse bater com o quadrado de outro, algo acontece
	public Rectangle getBounds(){
		return new Rectangle(x,y,largura,altura);//x,y e tamanho da imagem
		
	}
	public boolean isVisivel() {
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
