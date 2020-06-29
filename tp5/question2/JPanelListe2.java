package question2;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import java.util.*;

public class JPanelListe2 extends JPanel implements ActionListener, ItemListener {

    private JPanel cmd = new JPanel();
    private JLabel afficheur = new JLabel();
    private JTextField saisie = new JTextField();

    private JPanel panelBoutons = new JPanel();
    private JButton boutonRechercher = new JButton("rechercher");
    private JButton boutonRetirer = new JButton("retirer");

    private CheckboxGroup mode = new CheckboxGroup();
    private Checkbox ordreCroissant = new Checkbox("croissant", mode, false);
    private Checkbox ordreDecroissant = new Checkbox("décroissant", mode, false);

    private JButton boutonOccurrences = new JButton("occurrence");

    private JButton boutonAnnuler = new JButton("annuler");

    private TextArea texte = new TextArea();

    private java.util.List<String> liste;
    private Map<String, Integer> occurrences;
    
     private Stack<java.util.List<String>> dataStateStck;

    public JPanelListe2(java.util.List<String> liste, Map<String, Integer> occurrences) {
        this.liste = liste;
        this.occurrences = occurrences;

        cmd.setLayout(new GridLayout(3, 1));

        cmd.add(afficheur);
        cmd.add(saisie);

        panelBoutons.setLayout(new FlowLayout(FlowLayout.LEFT));
        panelBoutons.add(boutonRechercher);
        panelBoutons.add(boutonRetirer);
        panelBoutons.add(new JLabel("tri du texte :"));
        panelBoutons.add(ordreCroissant);
        panelBoutons.add(ordreDecroissant);
        panelBoutons.add(boutonOccurrences);
        panelBoutons.add(boutonAnnuler);
        cmd.add(panelBoutons);


        if(liste!=null && occurrences!=null){
            afficheur.setText(liste.getClass().getName() + " et "+ occurrences.getClass().getName());
            texte.setText(liste.toString());
        }else{
            texte.setText("la classe Chapitre2CoreJava semble incomplète");
        }

        setLayout(new BorderLayout());

        add(cmd, "North");
        add(texte, "Center");
        
        boutonAnnuler.addActionListener(this);
        saisie.addActionListener(this);
        boutonRechercher.addActionListener(this);
        boutonOccurrences.addActionListener(this);
        ordreDecroissant.addItemListener(this);
        ordreCroissant.addItemListener(this);
        boutonRetirer.addActionListener(this);
        boutonAnnuler.addActionListener(this);
    }

    public void actionPerformed(ActionEvent ae) {
        try {
            boolean res = false;
            if (ae.getSource() == boutonRechercher || ae.getSource() == saisie) {
                res = liste.contains(saisie.getText());
                Integer occur = occurrences.get(saisie.getText());
                afficheur.setText("résultat de la recherche de : "
                    + saisie.getText() + " -->  " + res);
            } else if (ae.getSource() == boutonRetirer) {
                res = retirerDeLaListeTousLesElementsCommencantPar(saisie
                    .getText());
                afficheur
                .setText("résultat du retrait de tous les éléments commençant par -->  "
                    + saisie.getText() + " : " + res);
            } else if (ae.getSource() == boutonOccurrences) {
                Integer occur = occurrences.get(saisie.getText());
                if (occur != null)
                    afficheur.setText(" -->  " + occur + " occurrence(s)");
                else
                    afficheur.setText(" -->  ??? ");
            }
            texte.setText(liste.toString());

        } catch (Exception e) {
            afficheur.setText(e.toString());
        }
    }

    public void itemStateChanged(ItemEvent ie) 
    {
        if(liste!=null&&ie!=null)
        {
            java.util.List<String> listSave = new ArrayList<String>(liste);
            boolean res = false;
            if (ie.getSource().equals(ordreCroissant))
            {
                res = true;
                if(res)
                  dataStateStck.push(listSave);
                Collections.sort(liste);
            }
            else if (ie.getSource().equals(ordreDecroissant)){
                res = true;
                if(res)
                    dataStateStck.push(listSave);
                Collections.sort(liste, Collections.reverseOrder());
            }
            texte.setText(liste.toString());
        }
    }

    private boolean retirerDeLaListeTousLesElementsCommencantPar(String prefixe)
    {
        if(prefixe!=null&&liste!=null)
        {
            boolean resultat = false;
            java.util.List<String> temp = liste;
            Iterator<String> iter = temp.iterator();
            while(iter.hasNext()) {
                String s = iter.next();
    
                if (s.startsWith(prefixe)) 
                {
                    iter.remove();
                    resultat = true;
                    occurrences.put(s, 0);
                }
            }
            return resultat;
        }
        return false;
    }  
}