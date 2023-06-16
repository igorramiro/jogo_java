package jogo;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Fase extends JPanel implements ActionListener {
	//instancia a imagem
	private ImageIcon fundo=new ImageIcon("res\\fundoFase.jpg");

	private int pontuacao;
	private Player player=new Player();
	private String player_nome;
	private Timer timer;

	private List<Lixo> lixo;
	private Latas_lixo latas=new Latas_lixo();

	private int nivel_dificuldade;
	private int partida=1;
	private int dificuldade[][]={//(numero de inimigo, espaçamento entre um lixo e outro)
		{10,300},//facil
		{20,200},//normal
		{40,100}//dificil
	};
	private Estilo estilo=new Estilo();
	private JLabel lbl_inicio = estilo.titulo("Voltar ao inicio",1);
	private JLabel lbl_jogarNovamente = estilo.titulo("Jogar novamente",1);

	public Fase(JFrame frame, String nome, int dificuldade) {
		setLayout(null);
		setFocusable(true);
		setDoubleBuffered(true);
		
		player_nome=nome;
		nivel_dificuldade=dificuldade;
		player.carrega_imagem();

		// Adiciona o WindowListener
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {//evento apos o frame ser descartado
                new Ranking(player_nome, pontuacao,nivel_dificuldade);
            }
        });

		lbl_inicio.setBounds(300,300,500,70);
		lbl_inicio.setVisible(false);
		lbl_inicio.setCursor(new Cursor(Cursor.HAND_CURSOR));

		lbl_jogarNovamente.setBounds(300,400,500,70);
		lbl_jogarNovamente.setVisible(false);
		lbl_jogarNovamente.setCursor(new Cursor(Cursor.HAND_CURSOR));

		JPanel  painel =new JPanel(null);

		painel.setVisible(true);
		painel.setBounds(0,0,1024,720);
		painel.setBackground(Color.white);

		painel.add(lbl_inicio);
		painel.add(lbl_jogarNovamente);
		add(painel);

		lbl_inicio.addMouseListener(estilo.acao_trocaTela(frame, 0));
		//ao clicar ele reinicia o jogo ou continua caso tenha alcançado o maximo de ponto 
		lbl_jogarNovamente.addMouseListener(new MouseAdapter()
		{  
			public void mouseClicked(MouseEvent e)  
			{  
				lbl_inicio.setVisible(false);
				lbl_jogarNovamente.setVisible(false);
				timer.start();
				inicializa_inimigo();
			}  
		});
		
		addKeyListener(new TecladoAdapter());
		
		timer=new Timer(5,this);
		timer.start();
		inicializa_inimigo();
	}

	//cria o lixo e coloca em um array
	public void inicializa_inimigo(){
		lixo=new ArrayList<Lixo>();
		
		Random gerador = new Random();
		for(int i=0;i<dificuldade[nivel_dificuldade][0];i++){
			int x=gerador.nextInt(1024-259);//-largura da img
			int y=0-i*dificuldade[nivel_dificuldade][1];
			lixo.add(new Lixo(x,y));
		}
	}
	
	//mostra oque vai estar sendo carregado na tela
	public void paint(Graphics g){
		Graphics2D graficos= (Graphics2D) g;
		Graphics2D escrita= (Graphics2D) g;
		escrita.setColor(Color.green);
		if(lixo.size()>0){
			escrita.setFont(new Font("ARIAL", 1, 25));
			//carrega uma imagem na tela
			graficos.drawImage(fundo.getImage(), 0, 0, this);
			graficos.drawImage(latas.lata_metal.getImage(), 0, 550, this);
			graficos.drawImage(latas.lata_papel.getImage(), 256, 550, this);
			graficos.drawImage(latas.lata_vidro.getImage(), 512, 550, this);
			graficos.drawImage(latas.lata_plastico.getImage(), 768, 550, this);
			graficos.drawImage(player.getImagem(), player.getX(), player.getY(), this);
			escrita.drawString("Pontuação:"+pontuacao, 830, 030);

			List<Tiro> tiros=player.getTiros();//como pode ter varios tiros ao mesmo tempo, colocamos em uma List para carregar 1 por 1
			for(int i=0;i< tiros.size();i++) {
				Tiro m=tiros.get(i);
				m.carrega_imagem();
				graficos.drawImage(m.getImagem(),m.getX(),m.getY(),this);
			}
			for(int j=0;j< lixo.size();j++) {
				Lixo e=lixo.get(j);
				e.carregar_imagem();
				graficos.drawImage(e.getImagem(),e.getX(),e.getY(),this);
				
			}
		}
		else{
			timer.stop();
			escrita.setFont(new Font("ARIAL", 100, 100));
			
			//carrega uma imagem na tela
			graficos.drawImage(fundo.getImage(), 0, 0, this);
			escrita.drawString("Pontuação:"+pontuacao, 100, 250);

			player.resetar_tiros();	
			if(pontuacao==(dificuldade[0][nivel_dificuldade]*partida)){
				lbl_jogarNovamente.setText("Continuar");
				partida+=1;
			}
			else{
				new Ranking(player_nome, pontuacao,nivel_dificuldade);
				lbl_jogarNovamente.setText("Jogar novamente");
				pontuacao=0;
			}

			lbl_inicio.setVisible(true);
			lbl_jogarNovamente.setVisible(true);

		}
		graficos.dispose();
	}
	
	public void checarColisao(){
		Rectangle FormaTiro;
		Rectangle FormaLixo;
		Rectangle FormaLata;

		for(int i=0;i<lixo.size();i++){//colisao entre lata e o lixo
			Lixo tempLixo=lixo.get(i);//cria um lixo temporario
			FormaLata=latas.getBounds(tempLixo.getTipo());
			FormaLixo=tempLixo.getBounds();
			if(FormaLata.intersects(FormaLixo)){
				player.setVisivel(false);
				tempLixo.setVisivel(false);
				pontuacao+=1;
			}
		}
		List<Tiro> tiros=player.getTiros();
		for (int j = 0; j < tiros.size(); j++) {//colisão entre o tiro e o lixo
			Tiro tempoTiro=tiros.get(j);//cria um lixo temporario
			FormaTiro=tempoTiro.getBounds();
			for (int o = 0; o < lixo.size(); o++) {
				Lixo tempLixo=lixo.get(o);//cria um lixo temporario
				FormaLixo=tempLixo.getBounds();
				if(FormaTiro.intersects(FormaLixo)){//move o lixo para esquerda ou direita dependendo do tiro
					if(tempoTiro.getTipo()==0){//para direita
						tempLixo.setX(tempLixo.getX()-75);
					}
					if(tempoTiro.getTipo()==1){//para esquerda
						tempLixo.setX(tempLixo.getX()+75);
					}
					tempoTiro.setVisivel(false);
				}
			}
			
		}
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		player.atualiza_imagem();
		
		List<Tiro> tiros=player.getTiros();
		for(int i=0;i< tiros.size();i++) {
			Tiro m=tiros.get(i);
			if(m.isVisivel()) {
				m.atualiza_imagem();
			}
			else {
				tiros.remove(i);
			}
		}
		for(int j=0;j< lixo.size();j++) {
			Lixo en=lixo.get(j);
			if(en.getisVisivel()){
				en.atualiza_imagem();
			}
			else{
				lixo.remove(j);
			}	
		}
		checarColisao();
		repaint();	
	}
	
	//instancia o metodo para mover o player
	private class TecladoAdapter extends KeyAdapter{
		@Override
		public void keyPressed(KeyEvent e){
			player.tecla_pressionada(e);
		}
		@Override
		public void keyReleased(KeyEvent e){
			player.tecla_liberada(e);
		}
	}
	

}
