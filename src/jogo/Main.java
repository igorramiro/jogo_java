package jogo;

import javax.swing.JFrame;
/*
 * colocar as açoes no Estilo tambem
 */
public class Main extends JFrame {
	private TelaInicio painel=new TelaInicio(this);
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Main(0);
	}
	public void fase(String nome, int dificuldade){
		add(new Fase(this,nome,dificuldade));
	}
	public Main(int tela) {
		setTitle("Recycle Shoot");
		setSize(1024,728);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
		if(tela==0){
			add(painel);
		}
		else if(tela==1){
			add(new IniciarJogo(this));
		}
		else if(tela==2){
			add(new ComoJogar(this));	
		}
		else if(tela==3){
			add(new Ranking(this).painel);
		}
	}

}
