namespace ProjectEuler
{
    public static class Solutions
    {
        /// <summary>
        /// Multiples of 3 and 5
        /// </summary>
        public static int Solution1()
        {
            var sum = 0;
            for (var i = 0; i < 1_000; i++)
                if (i % 3 == 0 || i % 5 == 0)
                    sum += i;

            return sum;
        }

        /// <summary>
        /// Even Fibonacci numbers
        /// </summary>
        public static int Solution2()
        {
            var x = 1;
            var y = 2;
            var sum = 0;

            while (x <= 4_000_000)
            {
                if (x % 2 == 0)
                    sum += x;

                var z = x + y;
                x = y;
                y = z;
            }

            return sum;
        }

        /// <summary>
        /// Largest prime factor
        /// </summary>
        public static long Solution3()
        {
            var value = 600851475143;
            var i = 2;

            while (i * i < value)
            {
                while (value % i == 0)
                    value /= i;
                i++;
            }

            return value;
        }

        /// <summary>
        /// Largest palindrome product
        /// </summary>
        public static int Solution4()
        {
            bool IsPalindrome(int value)
            {
                var str = value.ToString();
                var length = str.Length - 1;

                for (var i = 0; i < length - i; i++)
                    if (str[i] != str[length - i])
                        return false;

                return true;
            }

            var max = 0;
            for (var i = 999; i >= 100; i--)
            for (var j = 999; j >= 100; j--)
            {
                var x = i * j;
                if (x > max && IsPalindrome(x))
                    max = x;
            }

            return max;
        }

        /// <summary>
        /// Smallest multiple
        /// </summary>
        public static int Solution5()
        {
            int Gdc(int x, int y)
            {
                while (y != 0)
                {
                    var z = x;
                    x = y;
                    y = z % y;
                }

                return x;
            }

            int Lcm(int x, int y)
            {
                return x / Gdc(x, y) * y;
            }

            var result = 1;
            for (var i = 1; i <= 20; i++)
                result = Lcm(i, result);

            return result;
        }
    }
}