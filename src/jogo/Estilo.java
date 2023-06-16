package jogo;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.border.LineBorder;

public class Estilo {
    private int tipo_label[] = {
            100, // titulo
            50, // subtitulo
            30// normal
    };

    public JLabel titulo(String texto, int tipo) {
        JLabel label = new JLabel(texto);
        label.setFont(new Font("Algerian", 1, tipo_label[tipo]));// muda a fonte e tamanho da letra
        label.setForeground(Color.green);// mudar a cor da letra
        return label;
    }

    public JLabel seta_voltaInicio(JFrame frame) {
        JLabel seta = new JLabel("<html>&#10094;</html>");
        seta.setFont(new Font("ARIAL", 2, 100));// muda a fonte e tamanho da letra
        seta.setForeground(Color.green);// mudar a cor da letra
        seta.setBounds(0, 0, 75, 100);// define onde a seta vai ficar
        seta.setCursor(new Cursor(Cursor.HAND_CURSOR));// quando o cursor ficar em cima, vai parecer uma mâo

        seta.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                frame.dispose();
                new Main(0);
            }
        });
        return seta;
    }
    //ao clicar volta para a pagina de inicio
    public MouseAdapter acao_trocaTela(JFrame frame, int pagina) {
        return new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                frame.dispose();
                new Main(pagina);
            }
        };
    }

    public JButton botao(String texto) {
        JButton botao = new JButton(texto);
        botao.setForeground(Color.white);
        botao.setBackground(Color.green);// define o background do botao
        botao.setBorder(new LineBorder(Color.BLACK, 2));// define a cor e tipo da borda
        botao.setFont(new Font("ARIAL", 2, 20));
        return botao;
    }

}
