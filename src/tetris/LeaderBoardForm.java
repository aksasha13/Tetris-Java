
package tetris;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.RowSorter.SortKey;
import javax.swing.SortOrder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;


public class LeaderBoardForm extends javax.swing.JFrame {
   
    private DefaultTableModel tm;
    
    private String leaderboardFile="leaderboard";
    
    private TableRowSorter<TableModel> sorter;
    
    public LeaderBoardForm() {
        initComponents();
        initTableData();
        initTableSorter();
    }

    private void initTableData()
    {
        Vector ci = new Vector();
        ci.add("Player");
        ci.add("Score");
        
        tm = (DefaultTableModel) leaderBoard.getModel();
        try
        {
        FileInputStream fs = new FileInputStream(leaderboardFile);//creating new file "leaderboard" to store the score
        ObjectInputStream os = new  ObjectInputStream(fs);
        
        tm.setDataVector((Vector<Vector>)os.readObject(), ci);
        os.close();
        fs.close();
        }
        catch(Exception e){}
    }
    
    private  void initTableSorter()
    {
        sorter = new TableRowSorter<>(tm);
        leaderBoard.setRowSorter(sorter);
        
        ArrayList<SortKey> keys = new ArrayList<>();
        keys.add(new SortKey(1,SortOrder.DESCENDING) );
        
        sorter.setSortKeys(keys);
        
    }
        
    private void saveLeaderBoard()//saving the score on the long-term storage(on  file ,not on RAM) 
    {
        try
        {
            FileOutputStream fs = new FileOutputStream(leaderboardFile);//saving the score to file "leaderboard"
            ObjectOutputStream os = new ObjectOutputStream(fs);

            os.writeObject( tm.getDataVector() );

            os.close();
            fs.close();
        }
        catch(Exception e){}
       
    }
        
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnMainMenu = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        leaderBoard = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        btnMainMenu.setBackground(new java.awt.Color(255, 255, 255));
        btnMainMenu.setText("Main Menu");
        btnMainMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMainMenuActionPerformed(evt);
            }
        });

        leaderBoard.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Player", "Score"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(leaderBoard);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnMainMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(15, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnMainMenu)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 275, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnMainMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMainMenuActionPerformed
        
        this.setVisible(false);
        Tetris.showStartUp();
        
    }//GEN-LAST:event_btnMainMenuActionPerformed
    
    public void addPlayer(String playerName,int score)
    {
      tm.addRow(new Object[]{playerName,score});
      sorter.sort();
      
      saveLeaderBoard();
      this.setVisible(true);
      
    }
  
    public static void main(String args[]) {
        
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(LeaderBoardForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LeaderBoardForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LeaderBoardForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LeaderBoardForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        

        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LeaderBoardForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnMainMenu;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable leaderBoard;
    // End of variables declaration//GEN-END:variables
}
