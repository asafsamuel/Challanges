#include <stdio.h>
#include <string.h>
#include <math.h>
#include <stdlib.h>

int main()
{
	int t = 0;
	int p[1000002];
	char str[1000002];

	scanf("%d", &t);

	while (t--)
	{
		int len, i;
		int j = 0;

		scanf("%s", str + 1);
		len = strlen(str + 1);

		for (i = 2; i <= len; i++)
		{
			while ((j>0) && (str[j + 1] != str[i]))
				j = p[j];

			if (str[j + 1] == str[i])
				j++;

			p[i] = j;
		}

		printf("%d\n", len - p[len]);
	}

	return 0;
}