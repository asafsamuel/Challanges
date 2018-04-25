#include <cmath>
#include <cstdio>
#include <vector>
#include <iostream>
#include <algorithm>

using namespace std;

double ellip[41][5];
int n;

double dist(double x1, double y1, double x2, double y2) 
{
    return sqrt(pow(x2-x1, 2) + pow(y2-y1, 2));
}

bool check(double x, double y) 
{
    for(int i=0; i<n; i++) 
    {
        if(dist(ellip[i][0], ellip[i][1], x, y) + dist(ellip[i][2], ellip[i][3], x, y) < ellip[i][4]) 
            return true;
    }
    
    return false;
}

int main() 
{
    int T;
    cin >> T;
    
    while(T--) 
    {
        cin >> n;
        
        for(int j=0; j<n; j++) 
        {
            for(int k=0; k<5; k++)
                cin >> ellip[j][k];
        }

        int count = 0;
        for(double x=-50; x<50; x+=0.2) 
        {
            for(double y=-50; y<50; y+=0.2) 
            {
                if(!check(x, y)) 
                    count++;
            }
        }
        
        cout << round(count / 2500.0) << "%" << endl;
    }
    
    return 0;
}