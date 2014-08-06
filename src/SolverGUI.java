import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Created by travis on 8/5/14.
 */
public class SolverGUI extends JPanel {

    private final Color guiBackground = Color.WHITE;
    private final Color guiLineColor = Color.BLACK;
    private final Color guiSelected = Color.RED;
    private final Color guiTextColor = Color.DARK_GRAY;

    private final int GRID_ROWS = 9;
    private final int GRID_COLS = 9;

    private final int GUI_WIDTH = 540;
    private final int GUI_HEIGHT = 540;

    private final int GUI_CELL_PX = 60;

    public int selectedRow, selectedCol;

    public int[][] sudokuGrid;



    public SolverGUI() {
        this.selectedRow = -1;
        this.selectedCol = -1;
        this.sudokuGrid = new int[GRID_ROWS][GRID_COLS];

        setPreferredSize(new Dimension(GUI_WIDTH, GUI_HEIGHT));


        MouseAdapter mListener = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

                selectedRow = e.getY() / 60;
                selectedCol = e.getX() / 60;
                repaint();

            }
        };
        addMouseListener(mListener);
        repaint();
    }

    @Override
    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);

        Graphics2D graphics2D = (Graphics2D) graphics;

        graphics2D.setColor(guiBackground);
        graphics2D.fillRect(0, 0, GUI_WIDTH, GUI_HEIGHT);


        graphics.setColor(Color.BLACK);


        for (int i = 0; i <= 9; i++)
            for (int j = 0; j <= 9; j++) {
                graphics2D.drawLine(i * 60, j * 60, i * 60 + 60, j * 60);
                graphics2D.drawLine(i * 60, j * 60, i * 60, j * 60 + 60);

                if (i % 3 == 0) {
                    graphics2D.drawLine(i * 60 - 1, j * 60, i * 60 - 1, j * 60 + 60);
                    graphics2D.drawLine(i * 60 + 1, j * 60, i * 60 + 1, j * 60 + 60);
                }

                if (j % 3 == 0) {
                    graphics2D.drawLine(i * 60, j * 60 - 1, i * 60 + 60, j * 60 - 1);
                    graphics2D.drawLine(i * 60, j * 60 + 1, i * 60 + 60, j * 60 + 1);
                }
            }

        graphics2D.setColor(guiSelected);

        if (selectedCol != -1 && selectedRow != -1) {
            graphics2D.drawRect(selectedCol * 60, selectedRow * 60, 60, 60);
            graphics2D.drawRect(selectedCol * 60 + 1, selectedRow * 60 + 1, 58, 58);
            graphics2D.drawRect(selectedCol * 60 + 2, selectedRow * 60 + 2, 56, 56);
        }


        graphics2D.setColor(guiTextColor);
        graphics2D.setFont(new Font("pixelmix", Font.BOLD, 32));
        for (int i = 0; i < 9; i++)
            for (int j = 0; j < 9; j++)
                if (sudokuGrid[i][j] != 0)
                    graphics2D.drawString("" + sudokuGrid[i][j], j * 60 + 20, i * 60 + 45);

    }


    protected boolean legalMove(int i, int j, int val, int[][] cells) {
        for (int k = 0; k < 9; ++k)
            if (val == cells[k][j])
                return false;

        for (int k = 0; k < 9; ++k)
            if (val == cells[i][k])
                return false;

        int rowOffset = (i / 3) * 3;
        int colOffset = (j / 3) * 3;
        for (int k = 0; k < 3; ++k)
            for (int m = 0; m < 3; ++m)
                if (val == cells[rowOffset + k][colOffset + m])
                    return false;

        return true;
    }


    protected void solver() {

        long start = System.nanoTime();
        if (solve(0, sudokuGrid)) {
            long end = System.nanoTime();

            long length = end - start;
            repaint();
            System.out.println((long)length / 1000000000.0 + " seconds");
        }



        repaint();
    }

    protected boolean solve(int p, int[][] cells) {
        if (p == -1) return true; // no more positions
        if (p == -2) return false; //no solution; backtrack

        int i = p >> 4; // column number
        int j = p & 15; // row number

        if (cells[i][j] != 0)
            return solve(nextPosition(p), cells);

        for (int v = 1; v <= 9; ++v) {
            if (legalMove(i, j, v, cells)) {
                cells[i][j] = v;
                if (solve(nextPosition(p), cells))
                    return true;
            }
        }
        cells[i][j] = 0;
        return false;
    }

    private int nextPosition(int p) {
        int j = p & 15;
        if (j < 8)
            return p + 1;
        int i = p >> 4;
        if (i == 8) return -1;
        return (i + 1) << 4;

    }
}
