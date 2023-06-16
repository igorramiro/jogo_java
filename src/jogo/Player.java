package jogo;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;

public class Player {
	private int x,y;
	private int dx,dy;
	private int altura,largura;
	private Image imagem;
	private boolean isVisivel=false;
	private List <Tiro> tiros;//como os tiros são variaveis, colocamos em um array

	public Player() {
		x=100;
		y=440;
		isVisivel=true;
		
		tiros=new ArrayList<Tiro>();
	}
	public void carrega_imagem(){
		ImageIcon img_player=new ImageIcon("res\\player.png");
		imagem=img_player.getImage();
		
		altura=imagem.getHeight(null);
		largura=imagem.getWidth(null);
	}
	
	public void atualiza_imagem(){
		x+=dx;
		y+=dy;
	}

	public void resetar_tiros(){//limpa o array dos tiros para o caso do player querer jogar novamente
		tiros.clear();
	}

	public void tiro(int tipo_tiro) {
		this.tiros.add(new Tiro(x+(largura/2),y-60,tipo_tiro));//y-tamanho da imagem do tiro
	}
	public void tecla_pressionada(KeyEvent tecla){//move o personagem
		int codigo_tecla=tecla.getKeyCode();
		if(codigo_tecla==KeyEvent.VK_NUMPAD1 || codigo_tecla==KeyEvent.VK_1){
			tiro(0);
		}
		if(codigo_tecla==KeyEvent.VK_NUMPAD2 || codigo_tecla==KeyEvent.VK_2){
			tiro(1);
		}

		if(codigo_tecla==KeyEvent.VK_A){//para onde se movimentar
			dx=-25;//quanto se movimentar
		}
		if(tecla.getKeyCode()==KeyEvent.VK_D){
			dx=25;
		}
	}
	public void tecla_liberada(KeyEvent tecla){//faz o personagem parar de se mover
		int codigo_tecla=tecla.getKeyCode();
		if(codigo_tecla==KeyEvent.VK_A){
			dx=-0;
		}
		if(tecla.getKeyCode()==KeyEvent.VK_D){
			dx=0;
		}
	}
	//Gets e Sets
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public Image getImagem() {
		return imagem;
	}
	public List<Tiro> getTiros(){
		return tiros;
	}
	//cria um quadrado em volta da imagem, quando o quadrado desse bater com o quadrado de outro, algo acontece
	public Rectangle getBounds(){
		return new Rectangle(x,y,largura,altura);//x,y e tamanho da imagem
		
	}
	public boolean getisVisivel() {
		return isVisivel;
	}
	public void setVisivel(boolean isVisivel) {
		this.isVisivel = isVisivel;
	}
}
