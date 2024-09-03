import java.util.*;

class Database
{
    public static void main(String Arg[])
    {
        DBMS dobj = new DBMS();
        dobj.StartDBMS();
    }
}

class Student
{
    public int Rno;
    public String Name;
    public int Age;
    public int Marks;

    public static int Generator;

    static
    {
        Generator = 0;
    }

    public Student(String str, int X, int Y)
    {
        this.Rno = ++Generator;
        this.Name = str;
        this.Age = X;
        this.Marks = Y;
    }

    public void Display()
    {
        System.out.println(this.Rno + " " + this.Name + " " + this.Age + " " + this.Marks);
    }
}

class DBMS
{
    public LinkedList <Student> lobj; 

    public DBMS()
    {
        lobj = new LinkedList <Student> ();
    }

    public void StartDBMS()
    {
        System.out.println("Suraj DBMS started successfully...");
        System.out.println("Table schema created successfully...");
        System.out.println();

        String Query;
        String Tokens[];

        Scanner sobj = new Scanner(System.in);
        int TokensCount = 0;

        while(true)
        {
            System.out.print("Suraj DBMS > ");
            Query = sobj.nextLine();

            Tokens = Query.split(" ");

            TokensCount = Tokens.length;

            if(TokensCount == 1)
            {
                if("exit".equals(Tokens[0]))
                {
                    System.out.println("Thank you for using Suraj DBMS...");
                    break;
                }

                else if("help".equals(Tokens[0]))
                {
                    WriteQuery();
                }
            }

            else if(TokensCount == 2)
            {}

            else if(TokensCount == 3)
            {}

            else if(TokensCount == 4)
            {
                if("select".equals(Tokens[0]))
                {
                    SelectFrom();
                }
            }

            else if(TokensCount == 5)
            {
                if("select".equals(Tokens[0]))
                {
                    if("MAX".equals(Tokens[1]))
                    {
                        System.out.println("Maximum marks are : "+Aggregate_Max());
                    }

                    else if("MIN".equals(Tokens[1]))
                    {
                        System.out.println("Minimum marks are : "+Aggregate_Min());
                    }

                    else if("SUM".equals(Tokens[1]))
                    {
                        System.out.println("Summation of marks are : "+Aggregate_Sum());
                    }

                    else if("AVG".equals(Tokens[1]))
                    {
                        System.out.println("Average of marks are : "+Aggregate_Avg());
                    }

                    else if("count".equals(Tokens[1]))
                    {
                        System.out.println("Number of record in the table are : "+Count());
                    }
                } 
            }

            else if(TokensCount == 6)
            {}

            else if(TokensCount == 7)
            {
                if("insert".equals(Tokens[0]))
                {
                    InsertIntoTable(Tokens[4], Integer.parseInt(Tokens[5]),Integer.parseInt(Tokens[6]));
                }

                else if("delete".equals(Tokens[0]))
                {
                    DeleteFrom(Integer.parseInt(Tokens[6]));
                }
            }

            else if(TokensCount == 8)
            {  
                if("select".equals(Tokens[0]))
                {
                    SelectFrom(Tokens[7]);
                }
            }

            else if(TokensCount == 9)
            {

            }

            else if(TokensCount == 10)
            {
                if("update".equals(Tokens[0]))
                {
                    UpdateName(Tokens[5],Integer.parseInt(Tokens[9]));
                    // UpdateMarks(Integer.parseInt(Tokens[5]),Integer.parseInt(Tokens[9]));
                    // UpdateAge(Integer.parseInt(Tokens[5]),Integer.parseInt(Tokens[9]));
                }
            }

            else
            {
                System.out.println("Invalid count");
                System.out.println("Please enter valid query");
            }
        }
    }

    public void WriteQuery()
    {
        System.out.println("The Queries are shown below : ");

        System.out.println();

        System.out.print("To insert new record into the table : ");
        System.out.println("insert into student values student_name student_age student_marks");

        System.out.println();

        System.out.print("To display all the field from the table : ");
        System.out.println("select * from student");

        System.out.println();

        System.out.print("To display specific record from the table : ");
        System.out.println("select * from student where CONDITION");

        System.out.println();
        
        System.out.print("To display maximum marks from table : ");
        System.out.println("select MAX marks from student");

        System.out.println();

        System.out.print("To display minimum marks from table : ");
        System.out.println("select MIN marks from student");

        System.out.println();

        System.out.print("To display summation of marks from table : ");
        System.out.println("select SUM marks from student");

        System.out.println();

        System.out.print("To display average of marks from table : ");
        System.out.println("select AVG marks from student");

        System.out.println();

        System.out.print("To delete the record from the table : ");
        System.out.println("delete from student where CONDITION");

        System.out.println();

        System.out.print("To update the record from the table : ");
        System.out.println("update student set CONDITION where CONDITION");
    
        System.out.println();    

        System.out.print("To display number of record in the table : ");
        System.out.println("select count column_field from student");
    }

    // insert into student values(____, ____, ____);
    public void InsertIntoTable(String name, int age, int marks)
    {
        Student sobj = new Student(name,age,marks);
        lobj.add(sobj);
    }

    // select * from student
    public void SelectFrom()
    {
        if(lobj.size() == 0)
        {
            System.out.println("No record in the student table");
            return;    
        }

        System.out.println("Record from the student table are : ");

        for(Student sref : lobj)   
        {
            sref.Display();
        }
    } 

    // select * from  student where rno = 11
    public void SelectFrom(int no)
    {
        for(Student sref : lobj)
        {
            if(sref.Rno == no)
            {
                sref.Display();
                break;
            }
        }
    }

    // select * from  student where Name = 'Rahul'
    public void SelectFrom(String str)
    {
        for(Student sref : lobj)
        {
            if(str.equals(sref.Name))
            {
                sref.Display();
                break;
            }
        }
    }

    // select MAX(marks) from student
    public int Aggregate_Max()
    {
        Student temp = lobj.get(0);
        int iMax = temp.Marks;

        for(Student sref : lobj)
        {
            if(sref.Marks > iMax)
            {
                iMax = sref.Marks;
            }
        }

        return iMax;
    }

    // select MIN(marks) from student
    public int Aggregate_Min()
    {
        Student temp = lobj.get(0);
        int iMin = temp.Marks;

        for(Student sref : lobj)
        {
            if(sref.Marks < iMin)
            {
                iMin = sref.Marks;
            }
        }

        return iMin;
    }

    public int Aggregate_Sum()
    {
        int iSum = 0;

        for(Student sref : lobj)
        {
            iSum = iSum + sref.Marks;
        }

        return iSum;
    }

    public float Aggregate_Avg()
    {
        int iSum = 0;

        for(Student sref : lobj)
        {
            iSum = iSum + sref.Marks;
        }

        return (iSum / (lobj.size()));
    }

    public void DeleteFrom(int No)
    {
        int i = 0;

        for(Student sref : lobj)
        {
            if(sref.Rno == No)  
            {
                lobj.remove(i);
                break;
            }
            i++;
        }
    }

    public int Count()
    {
        int iCount = 0;

        for(Student sref : lobj)
        {
            ++iCount;
        }

        return iCount;
    }

    public void UpdateName(String str, int No)
    {
        for(Student sref : lobj)
        {
            if(sref.Rno == No)
            {
                sref.Name = str;
            }
        }
    }

    public void UpdateMarks(int iMarks, int No)
    {
        for(Student sref : lobj)
        {
            if(sref.Rno == No)
            {
                sref.Marks = iMarks;
            }
        }
    }
    
    public void UpdateAge(int iAge, int No)
    {
        for(Student sref : lobj)
        {
            if(sref.Rno == No)
            {
                sref.Age = iAge;
            }
        }
    }
}
