#include <cmath>
#include <cstdio>
#include <vector>
#include <iostream>
#include <algorithm>

using namespace std;

int main() 
{
    int q;
    cin >> q;
    
    while(q--) 
    {
        // Get inputs from the user
        int N, K;
        cin >> N >> K;
        
        // Get all dogs size from user
        vector<int> dogs;
        for(int i=0; i<N; i++) 
        {
            int d;
            cin >> d;
            dogs.push_back(d);
        }
        
        // Sort sizes
        sort(dogs.begin(), dogs.end());
        
        // Calc all the diffrences between each dog size
        vector<int> diff;
        for(int i=1; i<N; i++) 
            diff.push_back(dogs[i]-dogs[i-1]);
        
        // Sort diffrences
        sort(diff.begin(), diff.end());
        
        int cost = 0;
        for(int i=0; i<N-K; i++) 
            cost += diff[i];
        
        cout << cost << endl;
    }
    
    return 0;
}