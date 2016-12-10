import java.util.*;


public class SearchEngine{

	static MySort zz=new MySort();
	
	InvertedPageIndex ipi;
	
	public SearchEngine() 
	{ipi=new InvertedPageIndex();}

	Myset<String> allpages=new Myset<String>();
	LinkedList<PageEntry> allpgs=new LinkedList<PageEntry>();
	
	public void performAction(String actionMessage) {
		
		Scanner s=new Scanner (actionMessage);
		
		String am="";
		if(s.hasNext())
		{am=s.next();}
		
		if(am.equals("addPage"))
		{
			String page="";
			if(s.hasNext())
			{page=s.next();}
			
			if(allpages.IsMember(page)==false)
			{
				System.out.print(actionMessage+" : ");
				PageEntry newpage=new PageEntry(page);
				allpgs.add(newpage); // page entries of all pages
				ipi.addPage(newpage);
				allpages.Insert(page);
				
			}
			
		}
		else if(am.equals("queryFindPagesWhichContainWord"))
		{
			String word="",r="";
			if(s.hasNext())
			{word=s.next();}
		
			 r=word.toLowerCase();
			 if(r.equals("stacks"))
			 {r="stack";}
			 else if(r.equals("structures"))
			 {r="structure";}
			 else if(r.equals("applications"))
			 {r="application";}
			
		
			ArrayList <SearchResult> resultsofsearch=new ArrayList <SearchResult>();
			LinkedList <String> wordtofind=new LinkedList <String>();
			
			wordtofind.add(r);
			
			ListIterator<PageEntry> it=allpgs.listIterator(); 
		    while(it.hasNext())
		    {
		    	PageEntry r2=it.next();
		    	
		    	double relevance=r2.getRelevanceOfPage(wordtofind,1);
		    	if(relevance>0)
		    	{
		    		SearchResult sr=new SearchResult(r2,relevance);
		    		
		    		resultsofsearch.add(sr);
		    	}
		    	
		    }
		    
			ArrayList <SearchResult> finalresult=zz.sortThisList(resultsofsearch);
			
	    	int n=finalresult.size();
	    	if(n>0)
	    	{
	    	System.out.print(actionMessage+" : ");
	    	for(int i=0;i<n;++i)
	    	{	System.out.print(finalresult.get(i).page.pagename+" ");
	    		if(i<n-1)
	    		{System.out.print(", ");}
	    	}
	    	System.out.println();
	    	}
	    	else if(n==0)
	    	{System.out.println(actionMessage+" : ERROR-No webpage contains word "+r);}
	    	
	    	
		}
		else if(am.equals("queryFindPositionsOfWordInAPage"))
		{
			String word="",page="",r="";
			if(s.hasNext())
			{word=s.next();}
			if(s.hasNext())
			{page=s.next();}
			
			 r=word.toLowerCase();
			 if(r.equals("stacks"))
			 {r="stack";}
			 else if(r.equals("structures"))
			 {r="structure";}
			 else if(r.equals("applications"))
			 {r="application";}
					
			 
			 if(allpages.IsMember(page)==false)
			 {System.out.println(actionMessage+" : Exception- Page "+page+" does not exist in the database");
			 return;}
			 
			System.out.print(actionMessage+" : ");
			ipi.findpositions(r,page);
			System.out.println();
		}
		else if (am.equals("queryFindPagesWhichContainAllWords"))
		{
			LinkedList <String> wordstofind1=new LinkedList <String>();
			
			ArrayList <SearchResult> resultsofsearch=new ArrayList <SearchResult>();
			
			while(s.hasNext())
			{
				String r=s.next();
				r=r.toLowerCase();
				 if(r.equals("stacks"))
				 {r="stack";}
				 else if(r.equals("structures"))
				 {r="structure";}
				 else if(r.equals("applications"))
				 {r="application";}
				wordstofind1.add(r);}
			
			// have to find all pages which contain all of these words
			
			ListIterator<PageEntry> it=allpgs.listIterator(); 
		    while(it.hasNext())
		    {
		    	PageEntry r=it.next();
		    	
		    	double relevance=r.getRelevanceOfPage(wordstofind1,1);
		    	if(relevance>0)
		    	{
		    		SearchResult sr=new SearchResult(r,relevance);
		    		
		    		resultsofsearch.add(sr);
		    	}
		    	
		    }
		    	ArrayList <SearchResult> finalresult=zz.sortThisList(resultsofsearch);
				
		    	int n=finalresult.size();
		    	if(n>0)
		    	{
		    	System.out.print(actionMessage+" : ");
		    	for(int i=0;i<n;++i)
		    	{	System.out.print(finalresult.get(i).page.pagename+" ");
		    		if(i<n-1)
		    		{System.out.print(", ");}
		    	}
		    	System.out.println();
		    	}
		    	else if(n==0)
		    	{System.out.println(actionMessage+" : ERROR-No webpage contains all of these words");}
		    		    			
		}
		else if(am.equals("queryFindPagesWhichContainAnyOfTheseWords"))
		{
			LinkedList <String> wordstofind2=new LinkedList <String>();
			
			ArrayList <SearchResult> resultsofsearch=new ArrayList <SearchResult>();
			
			while(s.hasNext())
			{String r=s.next();
			r=r.toLowerCase();
			 if(r.equals("stacks"))
			 {r="stack";}
			 else if(r.equals("structures"))
			 {r="structure";}
			 else if(r.equals("applications"))
			 {r="application";}
			wordstofind2.add(r);}
			
			
			ListIterator<PageEntry> it=allpgs.listIterator(); 
		    while(it.hasNext())
		    {
		    	PageEntry r=it.next();
		    	
		    	double relevance=r.getRelevanceOfPage(wordstofind2,2);
		    	if(relevance>0)
		    	{
		    		SearchResult sr=new SearchResult(r,relevance);
		    		
		    		resultsofsearch.add(sr);
		    	}
		    	
		    }
		    
		    ArrayList <SearchResult> finalresult=zz.sortThisList(resultsofsearch);
			
	    	int n=finalresult.size();
	    	if(n>0)
	    	{
	    	System.out.print(actionMessage+" : ");
	    	for(int i=0;i<n;++i)
	    	{	System.out.print(finalresult.get(i).page.pagename+" ");
	    		if(i<n-1)
	    		{System.out.print(", ");}
	    	}
	    	System.out.println();
	    	}
	    	else if(n==0)
	    	{System.out.println(actionMessage+" : ERROR-No webpage contains any of these words");}
	    	
		    
		}
		else if(am.equals("queryFindPagesWhichContainPhrase"))
		{
			LinkedList <String> wordstofind3=new LinkedList <String>();
			while(s.hasNext())
			{String r=s.next();
			r=r.toLowerCase();
			 if(r.equals("stacks"))
			 {r="stack";}
			 else if(r.equals("structures"))
			 {r="structure";}
			 else if(r.equals("applications"))
			 {r="application";}
			wordstofind3.add(r);}
			
			
			ArrayList <SearchResult> resultsofsearch=new ArrayList <SearchResult>();
			
			
			ListIterator<PageEntry> it=allpgs.listIterator(); 
		    while(it.hasNext())
		    {
		    	PageEntry r=it.next();
		    	
		    	double relevance=r.getRelevanceOfPage(wordstofind3,3);
		    	//System.out.println("relevance= "+relevance);
				
		    	if(relevance>0)
		    	{	
		    		//System.out.println("resultsofsearch.size()= "+resultsofsearch.size());
					
		    		SearchResult sr=new SearchResult(r,relevance);
		    		
		    		resultsofsearch.add(sr);
		    	}
		    }
		    
	//		System.out.println("resultsofsearch.size()= "+resultsofsearch.size());		
		    
	    	ArrayList <SearchResult> finalresult=zz.sortThisList(resultsofsearch); // sorting in decreasing order of relevance
				
		    	int n=finalresult.size();
		     	//System.out.println("n= "+n);
		    	if(n>0)
		    	{
		    	System.out.print(actionMessage+" : ");
		    	for(int i=0;i<n;++i)
		    	{	System.out.print(finalresult.get(i).page.pagename+" ");
		    		if(i<n-1)
		    		{System.out.print(", ");}
		    	}
		    	System.out.println();
		    	}
		    	else if(n==0)
		    	{System.out.println(actionMessage+" : ERROR-No webpage contains this phrase");}
		    	
			
		}
				
	}

	
	void print() // prints the hash table
	{
		for(int h=0;h<100003;++h)
		{
			if(ipi.hashtable.listofwordentries[h].size()>0)
			{
		ListIterator<WordEntry> it=ipi.hashtable.listofwordentries[h].listIterator(0);
		while(it.hasNext())
		{
			WordEntry u=it.next();
			System.out.println(h+" "+u.word+"  "+u.entries.size());
		/*	if(u.word.equals("stack"))
			{
				Iterator<Position> it2=u.entries.iterator();
				while(it2.hasNext())
				{Position temp=it2.next();
					System.out.print(" "+temp.page.pagename+" "+temp.wordindex);}
			
				System.out.println();
			}*/
			
		}
			}
		} 
	}

}

/*
Write a Java class SearchResult which represents a tuple<page p, rel-
evance r>. SearchResult implements the Java interface Comparable
(http://docs.oracle.com/javase/7/docs/api/java/lang/Comparable.html).*/
class SearchResult
{
	PageEntry page;
	double relevance;
	
	public SearchResult(PageEntry p, double r)// Constructor method.
	{page=p;relevance=r;}
	public PageEntry getPageEntry()
	{return page;}
	public double getRelevance()
	{return relevance;}
	
	public int compareTo(SearchResult otherObject)//: Gives the ordering between the current object and the otherObject.
	{
		if(otherObject.relevance>relevance)//TODO
		{return 1;}
		else
		{return 0;}
	}
}


class MyHashTable// that implements the hashtable used by the InvertedPageIndex. It maps a word to its word-entry.
{ 
	LinkedList <WordEntry> listofwordentries[]=new LinkedList [100005];
 	MyHashTable()
 	{
 		for(int i=0;i<100005;++i)
		{listofwordentries[i]=new LinkedList <WordEntry>();}
	}
 
 	
 	//djb2 hash function 	
  int getHashIndex(String str) 
 {
	        int hash=5381,mod=100003;
	        int i=0,n=str.length();
	        
	        while (i<n)
	        {              	
	        if(hash>mod)				//taking modulo 100003 (a prime) to compress 
	        {hash%=mod;}                   
	        
	        hash = 33*hash +str.charAt(i);
	        ++i;
	     	}

	        hash%=mod;
	        return hash;	    
 }
 
  
 void addPositionsForWord(WordEntry w)// This adds an entry to the hashtable: stringName(w)  > positionList(w). If no word-entry exists, then create a new word entry. However, if a word-entry exists, then merge w with the existing word-entry.
 {
      int h=getHashIndex(w.word);
      
      int n=listofwordentries[h].size();
      
      ListIterator<WordEntry> it=listofwordentries[h].listIterator(); 
      
      
      while(it.hasNext())
      {
    	  WordEntry e=it.next();
    	  if(e.word.equals(w.word))//take union of word entries
    	  {
    		  //e is a wordentry
    		  WordEntry ret=e.join(w); // add w to e TODO
    		  listofwordentries[h].remove(e);
    		  listofwordentries[h].add(ret);
    		  
    		  return;
    	  }
      }
      
      
      
      /*WordEntry ret=new WordEntry(w.word);
      ListIterator<Position> it2=w.entries.listIterator(); 
      while(it2.hasNext())
      {
    	  ret.addPosition(it2.next());
      }*/
      listofwordentries[h].add(w);
      
 }
}




class InvertedPageIndex
{ 
	MyHashTable hashtable=new MyHashTable();

	void addPage(PageEntry p)// Add a new page entry p to the inverted page index.
	{
		Iterator<WordEntry> it=p.pageindex.wrdentries.iterator(); 
	      
		while(it.hasNext())
		{
			hashtable.addPositionsForWord(it.next());
		}
	}
	
    Myset<String> getPagesWhichContainWord(String str)// Return a set of page-entries of webpages which contain the word str.
	{
	     Myset<String> ret=new Myset<String>();
	     
	     int h=hashtable.getHashIndex(str);
	  //   int n=hashtable.listofwordentries[h].size();
	     
	     ListIterator<WordEntry> it=hashtable.listofwordentries[h].listIterator(); 
	     
	     while(it.hasNext())
	     {
	    	 WordEntry r=it.next();
	    	 if(r.word.equals(str)) // there is only one word entry for each word
	    	 {
	    		 Iterator<Position> it2=r.entries.iterator(); 
	    	     while(it2.hasNext())
	    	     {
	    	    	 Position z=it2.next();
	    	    	 ret.Insert(z.page.pagename);
	    	    	 //System.out.print(z.page.pagename);
	    	     }
	    	 }
	     }
	     
	     return ret;
	}
    
    void findpositions(String word,String page)
    {
    	int h=hashtable.getHashIndex(word);
    	ListIterator<WordEntry> it=hashtable.listofwordentries[h].listIterator(); 
	    
    	Myset<Integer> ret=new Myset<Integer>();
	     
    	
    	while(it.hasNext())
	    {
	    	WordEntry r=it.next();
	    	if(r.word.equals(word))
	    	{
	    		Iterator<Position> it2=r.entries.iterator(); 
	    		while(it2.hasNext())
	    		{
	    			Position u=it2.next();
	    			if(u.page.pagename.equals(page))
	    			{ret.Insert(u.wordindex);}
	    		}
	    	    
	    	}
	    }
    	
    	if(ret.size()==0)
    	{System.out.print("Webpage "+page+" does not contain word "+word);}
    	else if(ret.size()>0)
    	{
    		ListIterator<Integer> it3=ret.ll.listIterator(); 
    	    while(it3.hasNext())
    	    {
    	    	System.out.print(it3.next());
    	    	if(it3.hasNext())
    	    	{System.out.print(", ");}
    	    }
    	}
    }
      
}
