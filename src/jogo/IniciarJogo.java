package jogo;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class IniciarJogo extends JPanel {
    private int dificuldade=1;
    private JMenuBar menu = new JMenuBar();//cria o menu
    private JMenu menu_dificuldade = new JMenu("Normal");
    private Estilo estilo=new Estilo();

    public JMenuItem troca_opcaoMenu(JMenuItem item,String nome,int _dificuldade){
        item.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                menu_dificuldade.setText(nome);
                dificuldade=_dificuldade;
            }
        });
        return item;
    }

    public IniciarJogo(JFrame frame) {
        setLayout(null);
        JLabel titulo = estilo.titulo("Recycle Shoot",0);
        titulo.setBounds(100, 0, 900, 110);// define o local em que vai estar e suas medidas

        JLabel seta=estilo.seta_voltaInicio(frame);

		JButton btn_iniciarJogo =estilo.botao("Iniciar");
		btn_iniciarJogo.setBounds(437, 400, 150, 60);

        //input para colocar o nome do jogador que sera salvo no ranking
        JTextField input = new JTextField("Anonimo");
        input.setBounds(400, 200, 200, 30);

        menu.setBounds(400, 300, 200, 50);
        menu.setBackground(Color.green);

        menu_dificuldade.setFont(new Font("ARIAL", 2, 30));
        menu_dificuldade.setForeground(Color.WHITE);

        // define os itens do menu
        JMenuItem opt_facil = new JMenuItem("Facil");
        JMenuItem opt_normal = new JMenuItem("Normal");
        JMenuItem opt_dificil = new JMenuItem("Dificil");
        
        // Adiciona os itens ao menu "Dificuldade"
        menu_dificuldade.add(opt_facil);
        menu_dificuldade.add(opt_normal);
        menu_dificuldade.add(opt_dificil);
        menu.add(menu_dificuldade);

        menu_dificuldade.setPreferredSize(menu.getSize());//faz com que o menu cubra todo menuBar
        opt_dificil.setPreferredSize(new Dimension(200,21));//mudar 1 muda de tabela as outras opções

        // faz com que execute uma ação toda vez que mudar a opção selecionada no menu
        opt_facil=troca_opcaoMenu(opt_facil,"Facil",0);
        opt_normal=troca_opcaoMenu(opt_normal,"Normal",1);
        opt_dificil=troca_opcaoMenu(opt_dificil,"Dificil",2);

        btn_iniciarJogo.addActionListener(new ActionListener(){//evento do botão/troca de tela
			@Override
			public void actionPerformed(ActionEvent e){
				frame.dispose();
				Main fase=new Main(-1);
                fase.fase(input.getText(), dificuldade);
			}
		});

        add(seta);
        add(titulo);
        add(input);
        add(menu);
        add(btn_iniciarJogo);

    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D graficos = (Graphics2D) g.create();
        // instancia a imagem
        ImageIcon referencia = new ImageIcon("res\\fundoInicio.png");

        // carrega uma imagem na tela
        graficos.drawImage(referencia.getImage(), 0, 0, this);
    }
}
