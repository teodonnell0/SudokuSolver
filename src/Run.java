import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Created by travis on 8/5/14.
 */
public class Run extends JFrame {

    public Run() {
        super("Sudoku Solver");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        final SolverGUI gui = new SolverGUI();
        getContentPane().add(gui);
        pack();
        setVisible(true);

        KeyListener keyListener = new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                super.keyTyped(e);
                if (gui.selectedCol != -1 && gui.selectedRow != -1) {
                    switch (e.getKeyCode()) {
                        case KeyEvent.VK_1:
                            if (gui.legalMove(gui.selectedRow, gui.selectedCol, 1, gui.sudokuGrid))
                                gui.sudokuGrid[gui.selectedRow][gui.selectedCol] = 1;
                            break;
                        case KeyEvent.VK_2:
                            if (gui.legalMove(gui.selectedRow, gui.selectedCol, 2, gui.sudokuGrid))
                                gui.sudokuGrid[gui.selectedRow][gui.selectedCol] = 2;
                            break;
                        case KeyEvent.VK_3:
                            if (gui.legalMove(gui.selectedRow, gui.selectedCol, 3, gui.sudokuGrid))
                                gui.sudokuGrid[gui.selectedRow][gui.selectedCol] = 3;
                            break;
                        case KeyEvent.VK_4:
                            if (gui.legalMove(gui.selectedRow, gui.selectedCol, 4, gui.sudokuGrid))
                                gui.sudokuGrid[gui.selectedRow][gui.selectedCol] = 4;
                            break;
                        case KeyEvent.VK_5:
                            if (gui.legalMove(gui.selectedRow, gui.selectedCol, 5, gui.sudokuGrid))
                                gui.sudokuGrid[gui.selectedRow][gui.selectedCol] = 5;
                            break;
                        case KeyEvent.VK_6:
                            if (gui.legalMove(gui.selectedRow, gui.selectedCol, 6, gui.sudokuGrid))
                                gui.sudokuGrid[gui.selectedRow][gui.selectedCol] = 6;
                            break;
                        case KeyEvent.VK_7:
                            if (gui.legalMove(gui.selectedRow, gui.selectedCol, 7, gui.sudokuGrid))
                                gui.sudokuGrid[gui.selectedRow][gui.selectedCol] = 7;
                            break;
                        case KeyEvent.VK_8:
                            if (gui.legalMove(gui.selectedRow, gui.selectedCol, 8, gui.sudokuGrid))
                                gui.sudokuGrid[gui.selectedRow][gui.selectedCol] = 8;
                            break;
                        case KeyEvent.VK_9:
                            if (gui.legalMove(gui.selectedRow, gui.selectedCol, 9, gui.sudokuGrid))
                                gui.sudokuGrid[gui.selectedRow][gui.selectedCol] = 9;
                            break;

                        case KeyEvent.VK_SPACE:
                            gui.solver();
                            break;
                        default:
                            gui.sudokuGrid[gui.selectedRow][gui.selectedCol] = 0;
                            break;
                    }
                    gui.selectedCol = -1;
                    gui.selectedRow = -1;
                }

                if (KeyEvent.VK_SPACE == e.getKeyCode())
                    gui.solver();

                gui.repaint();
            }
        };

        addKeyListener(keyListener);
    }

    public static void main(String[] args) {
        new Run();
    }

}
