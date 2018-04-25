#include <cmath>
#include <cstdio>
#include <vector>
#include <iostream>
#include <algorithm>
#include <map>
#include <string>

#define PI 3.1415926

using namespace std;

struct Custom 
{
    int year, month, day, hour, minute, second;
    double lat, long_;
    
    int timeNumber() const 
    {
        return second + minute*60 + hour*3600 + day*86400 + month*2678400 + (year-1970)*980294400;
    }
    
    bool earlyThan(const Custom& that) 
    {
        return timeNumber() < that.timeNumber();
    }
    
    bool inRange(double r, double myLat, double myLong) const 
    {
        double d = 2 * 6378.137 * asin (sqrt ( pow(sin((lat-myLat)/2),2) + cos(lat) * cos(myLat) * pow(sin((long_-myLong)/2),2)));
        return d < r;
    }
};

double degree2radian(double degree) 
{
    return degree / 180 * PI;
}

int main() 
{
    double myLat, myLong, r;
    
    scanf("%lf,%lf", &myLat, &myLong);
    myLat = degree2radian(myLat);
    myLong = degree2radian(myLong);
    
    scanf("%lf", &r);
    map<long long, Custom> customs;

    string ignore;
    cin >> ignore;

    Custom c;
    long long phone;
    while(scanf("%d/%d/%d %d:%d,%lf,%lf,%lld", &c.day, &c.month, &c.year, &c.hour, &c.minute, &c.lat, &c.long_, &phone) != EOF) 
    {
        c.lat = degree2radian(c.lat);
        c.long_ = degree2radian(c.long_);
        
        if(customs.find(phone) == customs.end()) 
            customs[phone] = c;
        
        else 
            if(customs[phone].earlyThan(c)) 
                customs[phone] = c;
        
        c = customs[phone];
    }

    bool first = true;
    for(const auto kv : customs) 
    {
        if(kv.second.inRange(r, myLat, myLong)) 
        {
            if(!first) 
                cout << ',';
            
            first = false;
            cout << kv.first;
        }
    }

    return 0;
}