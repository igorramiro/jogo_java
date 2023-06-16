package jogo;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.Graphics;
import java.awt.Graphics2D;

public class TelaInicio extends JPanel {
	private Estilo estilo=new Estilo();
	
	public TelaInicio(JFrame frame){
		// Titulo/nome do jogo
		JLabel titulo=estilo.titulo( "Recycle Shoot",0 );
		titulo.setBounds(100, 100, 900, 120);//define o local em que vai estar e suas medidas

		//Botao para iniciar o jogo
		JButton btn_jogar =estilo.botao("Jogar");
		btn_jogar.setBounds(437, 300, 150, 60);
		
		//botao para a pagina de como jogar o jogo
		JButton btn_comoJogar =estilo.botao("Como jogar");
		btn_comoJogar.setBounds(437, 400, 150, 60);

		//botao para a pagina de onde aparece o ranking
		JButton btn_ranking =estilo.botao("Ranking");
		btn_ranking.setBounds(437, 500, 150, 60);
		
		setLayout(null);//como a disposição dos itens vai se comportar
		//null permite mover os objetos livre e independentemente

		//adiciona o titulo e botoes ao JPanel
		add(titulo);
		add(btn_comoJogar);
		add(btn_jogar);
		add(btn_ranking);

		btn_jogar.addMouseListener(estilo.acao_trocaTela(frame, 1));//evento do botão/troca de tela
		btn_comoJogar.addMouseListener(estilo.acao_trocaTela(frame, 2));
		btn_ranking.addMouseListener(estilo.acao_trocaTela(frame, 3));
	}
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		/*Em resumo, a chamada super.paintComponent(g) no método paintComponent(Graphics g) 
		garante que o painel seja limpo e preparado adequadamente antes de adicionar seus próprios elementos 
		de desenho ou lógica de renderização.*/
		//impede que os botoes apareçam apenas se passar o mouse em cima
		
		//Graphics2D graficos= (Graphics2D) g;
		Graphics2D graficos= (Graphics2D) g.create();
		//instancia a imagem
		ImageIcon referencia=new ImageIcon("res\\fundoInicio.png");
		
		//carrega uma imagem na tela
		graficos.drawImage(referencia.getImage(), 0, 0, this);
		//graficos.dispose(); colocando o dispose, o botao aparece apenas passe o mouse por cima
	}
}