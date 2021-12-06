
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.Border;
public class GameBoard2048 extends JPanel
{
   private JLabel[][] board;
   private int[][] matrix;
   private JLabel title;
   private JButton reset;
   private JButton upButton;
   private JButton leftButton;
   private JButton rightButton;
   private JButton downButton;
   private int layoutSize = 4;
   private int score = 0;
   
   public GameBoard2048()
   {
      setLayout(new BorderLayout());
      
      JPanel north = new JPanel();
      north.setLayout(new FlowLayout());
      add(north, BorderLayout.NORTH);
      title = new JLabel("Score: ");
      north.add(title);
      
      JPanel centerPanel = new JPanel();
      centerPanel.setLayout(new GridLayout(layoutSize,layoutSize));
      add(centerPanel, BorderLayout.CENTER);
      
      board = new JLabel[layoutSize][layoutSize];
      matrix = new int[layoutSize][layoutSize];
      for(int r = 0; r < layoutSize; r++)
         for(int c = 0; c < layoutSize; c++)
         {
        	matrix[r][c] = 0;
            board[r][c] = new JLabel();
            board[r][c].setBorder(BorderFactory.createLineBorder(Color.white));
            board[r][c].setOpaque(true);
            board[r][c].setBackground(Color.LIGHT_GRAY);
            board[r][c].setHorizontalAlignment(SwingConstants.CENTER);
            board[r][c].setVerticalAlignment(SwingConstants.CENTER);
            centerPanel.add(board[r][c]);
            
         }
      
      reset = new JButton("New Game");
      upButton = new JButton("Up");
      leftButton = new JButton("Left");
      downButton = new JButton("Down");
      rightButton = new JButton("Right");
      reset.addActionListener( new ResetListener() );
      upButton.addActionListener( new UpListener() );
      downButton.addActionListener( new DownListener() );
      leftButton.addActionListener( new LeftListener() );
      rightButton.addActionListener( new RightListener() );
      //add(reset, BorderLayout.SOUTH);
      
      JPanel controlPanel = new JPanel();
      controlPanel.setLayout(new BorderLayout());
      add(controlPanel, BorderLayout.SOUTH);
      controlPanel.add(upButton, BorderLayout.NORTH);
      controlPanel.add(leftButton, BorderLayout.WEST);
      controlPanel.add(reset, BorderLayout.CENTER);
      controlPanel.add(rightButton, BorderLayout.EAST);
      controlPanel.add(downButton, BorderLayout.SOUTH);
      
   }
   
   private void setTile(int row, int col, int tileVal)
   {
	   matrix[row][col] = tileVal;
	   if (tileVal == 0)
	   {
		   board[row][col].setText(null);
		   board[row][col].setBackground(Color.LIGHT_GRAY);
	   }
	   else if (tileVal <= 2048)
	   {
		   board[row][col].setText("" + tileVal);
		   board[row][col].setForeground(Color.black);
		   if (tileVal == 1)
		   {
			   Color Color1 = new Color(239,239,239);
			   board[row][col].setBackground(Color1);
		   }
		   else if (tileVal == 2)
		   {
			   Color Color2 = new Color(226,226,226);
			   board[row][col].setBackground(Color2);
		   }
		   else if (tileVal == 4)
		   {
			   Color Color4 = new Color(216,216,210);
			   board[row][col].setBackground(Color4);
		   }
		   else if (tileVal == 8)
		   {
			   Color Color8 = new Color(242,219,145);
			   board[row][col].setBackground(Color8);
		   }
		   else if (tileVal == 16)
		   {
			   Color Color16 = new Color(237,171,56);
			   board[row][col].setBackground(Color16);
		   }
		   else if (tileVal == 32)
		   {
			   Color Color32 = new Color(237,138,56);
			   board[row][col].setBackground(Color32);
		   }
		   else if (tileVal == 64)
		   {
			   Color Color64 = new Color(237,104,56);
			   board[row][col].setBackground(Color64);
		   }
		   else if (tileVal == 128)
		   {
			   Color Color128 = new Color(255,246,130);
			   board[row][col].setBackground(Color128);
		   }
		   else if (tileVal == 256)
		   {
			   Color Color256 = new Color(237,228,104);
			   board[row][col].setBackground(Color256);
		   }
		   else if (tileVal == 512)
		   {
			   Color Color512 = new Color(229,218,71);
			   board[row][col].setBackground(Color512);
		   }
		   else if (tileVal == 1024)
		   {
			   Color Color1024 = new Color(219,206,30);
			   board[row][col].setBackground(Color1024);
		   }
		   else if (tileVal == 2048)
		   {
			   Color Color2048 = new Color(204,187,0);
			   board[row][col].setBackground(Color2048);
		   }
	   }
	   else
	   {
		   board[row][col].setBackground(Color.black);
		   board[row][col].setText("" + tileVal);
		   board[row][col].setForeground(Color.white);
	   }
   }
   private void giveNewTile()
   {
	   int emptyTiles = 0;
	   int skips = 0;
	   for(int r = 0; r < layoutSize; r++)
	         for(int c = 0; c < layoutSize; c++)
	         {
	        	if(matrix[r][c] == 0)
	        	{
	        		emptyTiles++;
	        	}
	         }
	   
	   double randomNumber = Math.random();
	   int randomTile = 0;
	   if(randomNumber >= 0.5)
	   {
		   randomTile = 4;
	   }
	   else
	   {
		   randomTile = 2;
	   }
	   
	   skips = (int)(Math.random() * emptyTiles);
	   for(int r = 0; r < layoutSize; r++)
	         for(int c = 0; c < layoutSize; c++)
	         {
	        	if(matrix[r][c] == 0)
	        	{
	        		if (skips != 0)
	        		{
	        			skips--;
	        		}
	        		else
	        		{
	        			matrix[r][c] = randomTile;
	        			setTile(r, c, randomTile);
	        			return;
	        		}
	        	}
	         }
   }
   private class UpListener implements ActionListener //up
   {
	   private boolean collapseUp(int col)
	   {
		   boolean canMove = false;
		   int c = col;
		   for(int r = 0; r <= layoutSize-2; r++)
	         {
	        	if(matrix[r][c] == 0)
	        	{
	        		int searchCol = r+1;
	        		while(searchCol <= layoutSize-1 && matrix[searchCol][c] == 0)
	        		{
	        			searchCol++;
	        		}
	        		if (searchCol <= layoutSize-1)
	        		{
	        			canMove = true;
	        			matrix[r][c] = matrix[searchCol][c];
	        			matrix[searchCol][c] = 0;
	        		}
	        	}
	         }
		   return canMove;
	   }
      public void actionPerformed(ActionEvent e)
      {
    	  boolean canMove = false;
    	  
    	  for(int c = layoutSize-1; c >= 0; c--)
    	  	{
    		  if(canMove == false)
    		  {
    			  canMove = collapseUp(c);
    		  }
    		  else
    		  {
    			  collapseUp(c);
    		  }
    		  for(int r = 0; r < layoutSize-1; r++)
    		  {
    			  if(matrix[r][c] == matrix[r+1][c])
    			  {
    				  matrix[r][c] = (matrix[r][c] * 2);
    				  score = score + matrix[r][c];
    				  title.setText("Score: " + score);
    				  matrix[r+1][c] = 0;
    				  if(matrix[r][c] != 0)
    				  {
    					  canMove = true;
    				  }
    				  collapseUp(c);
    			  }
    		  }
    	  	}
    	  if (canMove == true)
    	  {
    		  giveNewTile();
    		  updateBoard();
    	  }
    	  if(hasLost() == true)
    	  {
    		  upButton.setEnabled(false);
    		  leftButton.setEnabled(false);
    		  downButton.setEnabled(false);
    		  rightButton.setEnabled(false);
    		  title.setText("You Lost! Your score was: " + score);
    	  }
      }
   }
   private class LeftListener implements ActionListener //left
   {
	   private boolean collapseLeft(int row)
	   {
		   boolean canMove = false;
		   int r = row;
		   for(int c = 0; c <= layoutSize-2; c++)
	         {
	        	if(matrix[r][c] == 0)
	        	{
	        		int searchCol = c+1;
	        		while(searchCol <= layoutSize-1 && matrix[r][searchCol] == 0)
	        		{
	        			searchCol++;
	        		}
	        		if (searchCol <= layoutSize-1)
	        		{
	        			canMove = true;
	        			matrix[r][c] = matrix[r][searchCol];
	        			matrix[r][searchCol] = 0;
	        		}
	        	}
	         }
		   return canMove;
	   }
      public void actionPerformed(ActionEvent e)
      {
    	  boolean canMove = false;
    	  
    	  for(int r = layoutSize-1; r >= 0; r--)
    	  	{
    		  if(canMove == false)
    		  {
    			  canMove = collapseLeft(r);
    		  }
    		  else
    		  {
    			  collapseLeft(r);
    		  }
    		  for(int c = 0; c < layoutSize-1; c++)
    		  {
    			  if(matrix[r][c] == matrix[r][c+1])
    			  {
    				  matrix[r][c] = (matrix[r][c] * 2);
    				  score = score + matrix[r][c];
    				  title.setText("Score: " + score);
    				  matrix[r][c+1] = 0;
    				  if(matrix[r][c] != 0)
    				  {
    					  canMove = true;
    				  }
    				  collapseLeft(r);
    			  }
    		  }
    	  	}
    	  if (canMove == true)
    	  {
    		  giveNewTile();
    		  updateBoard();
    	  }
    	  if(hasLost() == true)
    	  {
    		  upButton.setEnabled(false);
    		  leftButton.setEnabled(false);
    		  downButton.setEnabled(false);
    		  rightButton.setEnabled(false);
    		  title.setText("You Lost");
    	  }
      }
   }
   private void updateBoard()
   {
	   for(int r = 0; r < layoutSize; r++)
	         for(int c = 0; c < layoutSize; c++)
	         {
	        	setTile(r, c, matrix[r][c]);
	         }
   }
   private class RightListener implements ActionListener //right
   {
	   private boolean collapseRight(int row)
	   {
		   boolean canMove = false;
		   int r = row;
		   for(int c = layoutSize-1; c >= 1; c--)
	         {
	        	if(matrix[r][c] == 0)
	        	{
	        		int searchCol = c-1;
	        		while(searchCol >= 0 && matrix[r][searchCol] == 0)
	        		{
	        			searchCol--;
	        		}
	        		if (searchCol >= 0)
	        		{
	        			canMove = true;
	        			matrix[r][c] = matrix[r][searchCol];
	        			matrix[r][searchCol] = 0;
	        		}
	        	}
	         }
		   return canMove;
	   }
      public void actionPerformed(ActionEvent e)
      {
    	  boolean canMove = false;
    	  
    	  for(int r = 0; r < layoutSize; r++)
    	  	{
    		  if(canMove == false)
    		  {
    			  canMove = collapseRight(r);
    		  }
    		  else
    		  {
    			  collapseRight(r);
    		  }
    		  for(int c = layoutSize-1; c > 0; c--)
    		  {
    			  if(matrix[r][c] == matrix[r][c-1])
    			  {
    				  matrix[r][c] = (matrix[r][c] * 2);
    				  score = score + matrix[r][c];
    				  title.setText("Score: " + score);
    				  matrix[r][c-1] = 0;
    				  if(matrix[r][c] != 0)
    				  {
    					  canMove = true;
    				  }
    				  collapseRight(r);
    			  }
    		  }
    	  	}
    	  if (canMove == true)
    	  {
    		  giveNewTile();
    		  updateBoard();
    		  
    	  }
    	  if(hasLost() == true)
    	  {
    		  upButton.setEnabled(false);
    		  leftButton.setEnabled(false);
    		  downButton.setEnabled(false);
    		  rightButton.setEnabled(false);
    		  title.setText("You Lost");
    	  }
      }
   }
   private class DownListener implements ActionListener //down
   {
    	  private boolean collapseDown(int col)
   	   {
   		   boolean canMove = false;
   		   int c = col;
   		   for(int r = layoutSize-1; r >= 1; r--)
   	         {
   	        	if(matrix[r][c] == 0)
   	        	{
   	        		int searchCol = r-1;
   	        		while(searchCol >= 0 && matrix[searchCol][c] == 0)
   	        		{
   	        			searchCol--;
   	        		}
   	        		if (searchCol >= 0)
   	        		{
   	        			canMove = true;
   	        			matrix[r][c] = matrix[searchCol][c];
   	        			matrix[searchCol][c] = 0;
   	        		}
   	        	}
   	         }
   		   return canMove;
   	   }
         public void actionPerformed(ActionEvent e)
         {
       	  boolean canMove = false;
       	  
       	  for(int c = 0; c < layoutSize; c++)
       	  	{
       		  if(canMove == false)
       		  {
       			  canMove = collapseDown(c);
       		  }
       		  else
       		  {
       			  collapseDown(c);
       		  }
       		  for(int r = layoutSize-1; r > 0; r--)
       		  {
       			  if(matrix[r][c] == matrix[r-1][c])
       			  {
       				  matrix[r][c] = (matrix[r][c] * 2);
       				score = score + matrix[r][c];
       				title.setText("Score: " + score);
       				  matrix[r-1][c] = 0;
       				  if(matrix[r][c] != 0)
       				  {
       					  canMove = true;
       				  }
       				 collapseDown(c);
       			  }
       		  }
       	  	}
       	  if (canMove == true)
       	  {
       		  giveNewTile();
       		  updateBoard();
       		  
       	  }
       	  if(hasLost() == true)
       	  {
       		  upButton.setEnabled(false);
       		  leftButton.setEnabled(false);
       		  downButton.setEnabled(false);
       		  rightButton.setEnabled(false);
       		  title.setText("You Lost");
       	  }
         }
      }
   
   public boolean hasLost()
   {
	   //boolean lost = false;
	   int lastVal = 0;
	   for(int r = 0; r <= layoutSize-1; r++)
	   {
		   lastVal = matrix[r][0];
		   if (lastVal == 0)
		   {
			   return false;
		   }
		   for(int c = 1; c <= layoutSize-1; c++)
		   {
			   if(matrix[r][c] == 0 || matrix[r][c] ==lastVal)
			   {
				   return false;
			   }
			   lastVal = matrix[r][c];
		   }
	   }
	   for(int c = 0; c <= layoutSize-1; c++)
	   {
		   lastVal = matrix[0][c];
		   if (lastVal == 0)
		   {
			   return false;
		   }
		   for(int r = 1; r <= layoutSize-1; r++)
		   {
			   if(matrix[r][c] == 0 || matrix[r][c] ==lastVal)
			   {
				   return false;
			   }
			   lastVal = matrix[r][c];
		   }
	   }
	   return true;
   }
   private class ResetListener implements ActionListener //reset
   {
      public void actionPerformed(ActionEvent e)
      {
         for(int r = 0; r < layoutSize; r++)
            for(int c = 0; c < layoutSize; c++)
            {
            	setTile(r,c,0);
            	/*if((r + c)%2 == 1)
            		setTile(r, c, 2);
            	else
            		setTile(r, c, 4); */
            }
         
         score = 0;
         title.setText("Score: " + score);
       /*  setTile(0,0,0);
         setTile(1,0,1);
         setTile(1,1,2);
         setTile(1,2,4);
         setTile(1,3,8);
         setTile(1,4,16);
         setTile(1,5,32);
         setTile(1,6,64);
         setTile(1,7,128);
         setTile(2,0,256);
         setTile(2,1,512);
         setTile(2,2,1024);
         setTile(2,3,2048);
         setTile(2,4,4096); */
         
         giveNewTile();
        
   		  upButton.setEnabled(true);
   		  leftButton.setEnabled(true);
   		  downButton.setEnabled(true);
   		  rightButton.setEnabled(true);
   		  title.setText("2048");
      }
   }
}