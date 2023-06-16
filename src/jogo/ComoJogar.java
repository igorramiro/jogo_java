package jogo;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ComoJogar extends JPanel {
	private Estilo estilo=new Estilo();
    public ComoJogar(JFrame frame){
		
		setLayout(null);
        JLabel titulo=estilo.titulo( "Como Jogar",0 );
		titulo.setBounds(200, 0, 700, 110);//define o local em que vai ficar e suas medidas
        
		JLabel seta=estilo.seta_voltaInicio(frame);

		JLabel controles=new JLabel("<html>Atire nos lixos e redirecione-os para suas devidas lixeiras<br><br>"+
		"Acertando todas os lixos nas latas, você segue o jogo e aumenta sua pontuação<br><br>"+
		"&emsp;&emsp;&emsp;&emsp;&emsp; Controles/Teclas<br><br>"+
		"Player: <br>&emsp; A:move para esquerda <br>&emsp; D:move para direita <br><br>"+
		"Tiro: <br>&emsp; 1:move lixo para esquerda <br>&emsp; 2:move lixo para direita</html>");

		controles.setFont(new Font("ARIAL", 1, 25));
		controles.setForeground(Color.green);//mudar a cor da letra
		controles.setBounds(000, 110, 500, 450);

        add(seta);
        add(titulo);
		add(controles);
    }
    public void paintComponent(Graphics g){
		super.paintComponent(g);
		Graphics2D graficos= (Graphics2D) g.create();
		//instancia a imagem
		ImageIcon referencia=new ImageIcon("res\\fundoInicio.png");
		ImageIcon tutorial=new ImageIcon("res\\tutorial.png");
		
		//carrega uma imagem na tela
		graficos.drawImage(referencia.getImage(), 0, 0, this);
		graficos.drawImage(tutorial.getImage(), 500, 100, this);
	}
}
