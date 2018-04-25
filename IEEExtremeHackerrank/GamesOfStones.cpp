#include <cmath>
#include <cstdio>
#include <vector>
#include <iostream>
#include <algorithm>
using namespace std;

int main() 
{
    int T;
    cin >> T;
    
    for(int t=0; t<T; t++)
    {
        int G;
        cin >> G;
        bool alice = false;
        
        for(int g=0; g<G; g++) 
        {
            int P;
            cin >> P;
            for(int p=0; p<P; p++) 
            {
                int n;
                cin >> n;
                alice ^= (n >> 1) & 1; // one bit binary addition
            }
        }
        
        if(alice)
            cout << "Alice" << endl;
        else
            
            cout << "Bob" << endl;
    }
    return 0;
}