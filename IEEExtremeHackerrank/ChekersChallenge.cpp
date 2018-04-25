#include <cmath>
#include <cstdio>
#include <vector>
#include <iostream>
#include <algorithm>
using namespace std;

// check whether (x,y) is a legal location
bool legal(int x, int y) 
{
    return (x>=0) && (x<8) && (y>=0) && (y<8);
}

int countWin(char board[8][8], bool isKing, int wx, int wy, int lastDir, int numBlack) {
    int count = 0;
    
    // game over, white piece win
    if(numBlack == 0) return 1;
    
    int dir[4][2] = { {0,1}, {0,-1}, {-1,0}, {1,0} };
    int bx, by; // black piece to the left, right or upwards
    int sx, sy; // landing square
    
    if(!isKing) 
	{
        // cannot go downwards, possible directions: 0, 1, 2
        for(int d=0; d<3; d++) {
            
            bx = wx + dir[d][0];
            by = wy + dir[d][1];
            sx = wx + dir[d][0] * 2;
            sy = wy + dir[d][1] * 2;
            
            if(board[bx][by]=='x' && legal(sx, sy) && board[sx][sy]=='.') 
			{
                if(sx == 0) isKing = true;
                board[bx][by] = '.';
                numBlack--;
                count += countWin(board, isKing, sx, sy, d, numBlack);
				
                // backtrack
                board[bx][by] = 'x';
                numBlack++;
            }
        }
    }
	
    else 
	{
        for(int d=0; d<4; d++) 
		{
            if((d==0 && lastDir==1) || (d==1 && lastDir==0) || (d==2 && lastDir==3) || (d==3 && lastDir==2)) 
                continue;
			
            bx = by = -1;
			
            // white king can go at least 1 step, at most 6 steps
            for(int skipBefore=1; skipBefore<=6; skipBefore++) {
                int tx = wx + dir[d][0] * skipBefore;
                int ty = wy + dir[d][1] * skipBefore;
                if(legal(tx, ty) && board[tx][ty]=='x') {
                    bx = tx;
                    by = ty;
                    break;
                }
            }
			
            if(!legal(bx, by)) continue;
            for(int skipAfter=1; skipAfter<=6; skipAfter++) 
			{
                int tx = bx + dir[d][0] * skipAfter;
                int ty = by + dir[d][1] * skipAfter;
                if(legal(tx, ty) && board[tx][ty]=='.') 
				{
                    board[bx][by] = '.';
                    numBlack--;
                    int C = countWin(board, isKing, tx, ty, d, numBlack);
                    count += C;
					
                    // backtrack
                    board[bx][by] = 'x';
                    numBlack++;
                }
				
                else					
                    break;
                
            }
        }
    }

    return count;
}


int main() 
{
    int T;
    cin >> T;
    for(int t=0; t<T; t++) 
	{
        char board[8][8];
        for(int l=0; l<8; l++) 
		{
            cin >> board[l];
        }
        
        // check whether white piece is king or not
        bool isKing = false;
        for(int c=0; c<8; c++) {
            if(board[0][c] == 'o') isKing = true;
        }
        
        // locate white piece
        int wx, wy, numBlack = 0;
        for(int l=0; l<8; l++) 
		{
            for(int c=0; c<8; c++) 
			{
                if(board[l][c] == 'o') 
				{
                    wx = l;
                    wy = c;
                    board[l][c] = '.';
                }
                else if(board[l][c] == 'x') 
				{
                    numBlack++;
                }
            }
        }
		
        cout << countWin(board, isKing, wx, wy, -1, numBlack) << endl;
        getchar();
    }
    return 0;
}