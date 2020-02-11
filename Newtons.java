import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class Newtons {
	public static void main(String[] args) throws FileNotFoundException{
		
		System.out.print("Enter file name that contains the data points: ");
		Scanner sc = new Scanner(System.in);
		String input = sc.nextLine();
		
		File data = new File(input);
		File data2 = new File(input);
		Scanner scan = new Scanner(data);
		Scanner scan2 = new Scanner(data2);
		int count = 0;
		while(scan.hasNextFloat())
		{
			scan.nextFloat();
			count++;
		}
		float[] xs = new float[count/2];
		float[] ys = new float[count/2];
		count = 0;
		int y = 0;
		while(scan2.hasNextFloat())
		{
			if(count < xs.length)
			{
				xs[count] = scan2.nextFloat();
				count++;
			}
			else
			{
				ys[y] = scan2.nextFloat();
				y++;
			}
		}
		scan.close();
		scan2.close();
		Float z = null;
		float[] cs = coeff(xs, ys);
		System.out.print("Enter value to evaluate polynomial: ");
		String point = sc.nextLine();		
		while(!point.equals("q"))
		{
			z = Float.parseFloat(point);
			System.out.println(evalNewton(xs, cs, z));
			System.out.println();
			System.out.print("Enter value to evaluate polynomial: ");
			point = sc.nextLine();
		}
		sc.close();
		if(point.equals("q"))
			System.exit(0);
	}
	public static float[] coeff(float[] xs, float[] ys)
	{
		int n = ys.length;
		float[] cs = new float[n];
		for(int i = 0; i<n; i++)
		{
			cs[i] = ys[i];
		}
		for(int j = 1; j<=n; j++)
		{
			for(int i = n-1; i > j; i--)
			{
				cs[i] = ((cs[i] - cs[i-1]) / (xs[i] - xs[i-j]));
			}
		}
		return cs;
	}
	
	public static float evalNewton(float[] xs, float[] cs, float z)
	{
		int n = xs.length-1;
		float result = cs[n];
		
		for(int i = n; i >= 0; i--)
		{
			result = result * (z - xs[i]) + cs[i];
		}
		
		return result;
	}
}
