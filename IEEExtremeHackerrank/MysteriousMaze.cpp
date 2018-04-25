#include <cmath>
#include <cstdio>
#include <vector>
#include <iostream>
#include <algorithm>
using namespace std;

int find(vector<int> &parent, int x) 
{
    int px;
    
    if(parent[x] < 0) 
        px = x;
    
    else 
    {
        px = find(parent, parent[x]);
        parent[x] = px;
    }
    
    return px;
}

void union_(vector<int> &parent, int x, int y) 
{
    int px = find(parent, x);
    int py = find(parent, y);
    
    if(px != py) 
    {
        if(parent[px] < parent[py]) 
        {
            parent[px] += parent[py];
            parent[py] = px;
        }
        else {
            parent[py] += parent[px];
            parent[px] = py;
        }
    }
}

bool legal(int x, int y, int H) 
{
    return (x >=1 && x <= H) && (y >= 1 && y <=H);
}

int pos2Index(int x, int y, int H) 
{
    return (x-1) * H + (y-1);
}

int main() 
{
    int H;
    cin >> H;
    vector<int> parent(H*H+2, -1);
    vector<vector<bool> > maze(H+2, vector<bool>(H+2, 0));
    
    for(int i=1; i<=H; i++) 
    {
        union_(parent, pos2Index(1, i, H), H*H);
        union_(parent, pos2Index(H, i, H), H*H+1);
    }
    
    int dir[4][2] = {{0,1}, {0,-1}, {1,0}, {-1,0}};
    int count = 1;
    
    while(true) 
    {
        int x, y;
        cin >> x;
        
        if(x == -1) 
        {
            cout << -1 << endl;
            break;
        }
        
        cin >> y;
        maze[x][y] = true;
        for(int d=0; d<4; d++) 
        {
            int nx = x + dir[d][0];
            int ny = y + dir[d][1];
            
            if(legal(nx, ny, H) && maze[nx][ny]) 
            {
                union_(parent, pos2Index(x, y, H), pos2Index(nx, ny, H));
            }
        }
        
        if(find(parent, H*H) == find(parent, H*H+1))
        {
            cout << count;
            break;
        }
        
        count++;
    }
    
    return 0;
}