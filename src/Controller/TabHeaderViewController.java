package Controller;

import Constants.Colors;
import View.Acceuil.TabHeader;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class TabHeaderViewController implements MouseListener {
    //attributs
    TabHeader tabHeader;

    //Constructeur
    public TabHeaderViewController(TabHeader tabHeader) {
        this.tabHeader = tabHeader;
        changePanelState(TabHeader.STATE_NORMAL);
        this.tabHeader.addMouseListener(this);
    }

    public void changePanelState(String constantStateTab) {
        tabHeader.setState(constantStateTab);

        if(constantStateTab.equals(TabHeader.STATE_NORMAL)) {
            tabHeader.setBackground(Colors.green);
            tabHeader.getTitleLabel().setHorizontalAlignment(SwingConstants.CENTER);
            tabHeader.getTitleLabel().setForeground(Colors.white);
        } else if(constantStateTab.equals(TabHeader.STATE_SELECTED)) {
            tabHeader.setBackground(Colors.green);
            tabHeader.getTitleLabel().setHorizontalAlignment(SwingConstants.CENTER);
            tabHeader.getTitleLabel().setForeground(Colors.gris);
        } else if(constantStateTab.equals(TabHeader.STATE_ACTIVATED)) {
            tabHeader.setBackground(Colors.green);
            tabHeader.getTitleLabel().setHorizontalAlignment(SwingConstants.CENTER);
            tabHeader.getTitleLabel().setForeground(Colors.gris);
        }
    }

    //Getters & Setters
    public TabHeader getTabHeader() {
        return tabHeader;
    }
    public void setTabHeader(TabHeader tabHeader) {
        this.tabHeader = tabHeader;
    }

    //Mouse Listener
    @Override
    public void mouseClicked(MouseEvent e) {
        if(this.tabHeader.getState().equals(TabHeader.STATE_NORMAL)
                || this.tabHeader.getState().equals(TabHeader.STATE_SELECTED)) {
            this.changePanelState(TabHeader.STATE_ACTIVATED);
        } else if(this.tabHeader.getState().equals(TabHeader.STATE_ACTIVATED)) {
            this.changePanelState(TabHeader.STATE_NORMAL);
        }
    }
    @Override
    public void mousePressed(MouseEvent e) {

    }
    @Override
    public void mouseReleased(MouseEvent e) {

    }
    @Override
    public void mouseEntered(MouseEvent e) {
        if(this.getTabHeader().getState().equals(TabHeader.STATE_NORMAL)) {
            changePanelState(TabHeader.STATE_SELECTED);
        }
    }
    @Override
    public void mouseExited(MouseEvent e) {
        if(this.getTabHeader().getState().equals(TabHeader.STATE_SELECTED)) {
            changePanelState(TabHeader.STATE_NORMAL);
        }
    }
}
