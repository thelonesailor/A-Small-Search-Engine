import java.util.*;
import java.io.*;


public class Myset <X>
{
	LinkedList<X> ll=new LinkedList<X>();  
	
	 public Boolean IsEmpty()  // returns true if the set is empty
	 {
		 if(ll.size()==0)
		 {return true;}
		 else
		 {return false;}
	 }
	 public int size()
	 {return ll.size();}
	 
		 public Boolean IsMember(X o)// Returns true if o is in the set, false otherwise.
		 {
			 ListIterator<X> it=ll.listIterator(0);
	
			 while(it.hasNext())
			 {
				 X r=it.next();
				 if(r.equals(o))
				 {return true;}
			 }
			 
			 return false;
		  }
		 
		 public void Insert(X o)// Inserts o into the set.
		 {
			 if(!IsMember(o))
			 {ll.add(o);}
		 }
		 
		
		 public void Delete(X o)// Deletes o from the set, throws exception if o is not in the set.
		 {
			 ListIterator<X> it=ll.listIterator(0);
			 while(it.hasNext())
			 {
				 X r=it.next();
				 if(r.equals(o))
				 {ll.removeFirstOccurrence(o);
				 return;}
			 }

			 System . out . print ("ERROR- Can't delete object is not in the set ");
			 return;
		 }
		
		 public Myset<X> Union(Myset<X> a)// Returns a set which is the union of the current set with the set a
		 {
			 Myset<X> newset=new Myset<X>();
			 ListIterator<X> it=ll.listIterator(0);
			 
			 while(it.hasNext())
			 {
				 X u=it.next();
				 newset.Insert(u);
			 }
			 
			 ListIterator<X> it2=a.ll.listIterator(0);
			
			 while(it2.hasNext())
			 {
				 X u=it2.next();
				 newset.Insert(u);
			 }
			 
			 return newset;
		 }
		 public Myset<X> Intersection(Myset<X> a)// Returns a set which is the intersection of the current set with the set a
		 {
			 Myset<X> newset=new Myset<X>();
			 ListIterator<X> it=ll.listIterator(0);
			 
			 while(it.hasNext())
			 {
				 X u=it.next();
				 if(a.IsMember(u))  // u is in both a and current set
				 {newset.Insert(u);}
			 }
			 
			 return newset;
		 }
		
	
}



class MyLinkedList<T> implements Iterable<T> 
 {
    private Node<T> head;
    int size=0;
    public MyLinkedList() {
        super();
        this.head = null;
        
    }

    
    public void addFirst(T data) {
        Node<T> newNode = new Node<T>(data, head);
        head = newNode;
        ++size;
    }

    public void add(T data) {

        if ( head == null )
        {
            head = new Node<T>(data, null);
            return;
        }

        Node<T> tempNode = head;
        while (tempNode.next != null) {
            tempNode = tempNode.next;
        }
        tempNode.next = new Node<T>(data, null);
        ++size;
    }

    public int size()
    {return size;}
    
    public T getNode() {
        return head.data;
    }

    @Override
    public Iterator<T> iterator() {
        return new ListIterator<T>();
    }

     class ListIterator<T> implements Iterator<T> 
     {
        private Node<T> currentNode;
        private Node<T> previous;

       
        public ListIterator() {
            super();
            this.currentNode = (Node<T>) head;
            this.previous = null;
        }

        @Override
        public boolean hasNext() {
            if (currentNode != null && currentNode.next != null)
                return true;
            else
                return false;
        }

        @Override
        public T next() {
            if (!hasNext())
                throw new NoSuchElementException();
            if ( previous == null )
            {
                previous = currentNode;
                return previous.data;
            }
            T node = currentNode.data;
            currentNode = currentNode.next;
            return currentNode.data;
        }

        
    }

    
    private static class Node<T> {
        private T data;
        private Node<T> next;

        public Node(T data, Node<T> next) {
            super();
            this.data = data;
            this.next = next;
        }

        
        public Node(Node<T> next) {
            super();
            this.data = null;
            this.next = next;
        }
    }
 }



class Position
{
	PageEntry page;
	int wordindex;
	
	Position(PageEntry p,int wordIndex)
	{
		page=p;
		wordindex=wordIndex;
	}
	
	PageEntry getpageentry()
	{return page;}
	
	int getWordIndex() 		
	{return wordindex;}
	
}


//Write a Java class WordEntry. For a string str, this class stores the list of word indice's where str is present in the document(s)
class WordEntry
{ 
	avlnode root;
	String word;
	LinkedList <Position> entries=new LinkedList<Position>();
	
	WordEntry(String Word)//The argument is the word for which we are creating the word entry.
	{word=Word;root=null;}
	
   void addPosition(Position position)// Add a position entry for str.
   {entries.add(position);
  
   avlnode u=new avlnode(position);	  // inserting in avl tree
   avlinsert(root,u);
   }
  
    void addPositions(LinkedList<Position> positions)// Add multiple position entries for str
	{
  	ListIterator<Position> it=positions.listIterator();
  	
  	while(it.hasNext())
  	{Position temp=it.next();
  	 entries.add(temp);
  	 
  	 avlnode u=new avlnode(temp);	  // inserting in avl tree
     avlinsert(root,u);}
		
	}
  
  
    LinkedList<Position> getAllPositionsForThisWord()
	{return entries;}
  
    
  WordEntry join(WordEntry w)
  {
	  
	  WordEntry ret=new WordEntry(w.word);
	  
	  Iterator<Position> it=entries.iterator();
	  while(it.hasNext())
	  {
		  ret.addPosition(it.next());
	  }
	  
	  Iterator<Position> it2=w.entries.iterator();
	
	 while(it2.hasNext())
	  {int u=0;
		  Position z=it2.next();
		  it=entries.iterator();
		  while(it.hasNext())
		  {
			  Position r=it.next();
			  if(z.page==r.page && z.wordindex==r.wordindex)
			  {u=1;break;}
		  }
		  
		  if(u==0)
		  {ret.addPosition(z);}
		    
		  	  
	  }
	  
  
	  return ret;
  }
  
  class avlnode
  {
	  Position position;
	  int height=0,key;
	  avlnode left,right,parent;
	  
	  avlnode(Position o)
	  {position=o;height=0;key=position.wordindex;
	  left=null;right=null;parent=null;}
  }
  
  
  
  int gtbl(avlnode u) 
  {
      if (u == null) 
      {return 0;}
  
      return u.left.height - u.right.height;
  }
  
  avlnode rightRotate(avlnode y) 
  {
	  avlnode x = y.left;
	  avlnode T2 = x.right;

      x.right = y;
      y.left = T2;

      y.height = Math.max(y.left.height,y.right.height) + 1;
      x.height = Math.max(x.left.height,x.right.height) + 1;

      return x;
  }

  avlnode leftRotate(avlnode x) 
  {
	  avlnode y = x.right;
	  avlnode T2 = y.left;

      y.left = x;
      x.right = T2;

      x.height = Math.max(x.left.height,x.right.height) + 1;
      y.height = Math.max(y.left.height,y.right.height) + 1;

      return y;
  }

  
  avlnode avlinsert(avlnode node,avlnode pos)
  {
	  if(node==null)
	  {return pos;}
	  
	  if (pos.key < node.key) {
          node.left = avlinsert(node.left,pos);
      } else {
          node.right = avlinsert(node.right,pos);
      }
	  
	  node.height = Math.max(node.left.height,node.right.height) + 1;
	  
	  int balance = gtbl(node);
	  
	  
	  if (balance > 1 && pos.key < node.left.key) {
          return rightRotate(node);
      }

      if (balance < -1 && pos.key > node.right.key) {
          return leftRotate(node);
      }

      if (balance > 1 && pos.key > node.left.key) {
          node.left = leftRotate(node.left);
          return rightRotate(node);
      }

      if (balance < -1 && pos.key < node.right.key) {
          node.right = rightRotate(node.right);
          return leftRotate(node);
      }
      
      
	 return node;
	  
  }
  
  avlnode successor(avlnode node) //returns successor of a particular node
  {
	  avlnode temp=null;
	  if(node.right!=null)
	  {
		  temp=node.right;
		  while(temp.left!=null)
		  {
			  temp=temp.left;
		  }
		  
	  }
	  else if(node.right==null) //find the last left turn from the root to this node
	  {
		  temp=root;
		  int key=node.position.wordindex;
		  avlnode lastleft=null;
		  
		  while(temp!=null)
		  {
			  if(temp.key<key)
			  {temp=temp.right;}
			  else if(key<temp.key)
			  {lastleft=temp;  //!! write it before
			  temp=temp.left;}
			  else
			  {break;}
		  }
		  
		  
		  temp=lastleft;
		}
	  
	  return temp; 
  }
 
  
/*  Implement the collection of position entries for the WordEntry class as
  an AVL-tree which is a height-balanced binary search tree. The data
  item for each node in the the tree is a position entry, and the ordering
  of the nodes in the tree is on the basis of the word index in the position
  entry. For phrase queries, you must use the AVL tree to nd the next
  word.*/
}


//
class PageIndex  //stores one word-entry for each unique word in the document
{ 
	
	LinkedList <WordEntry> wrdentries=new LinkedList<WordEntry>();  //linked list of all word entries in a page 
	
	avlnode root;
	PageIndex()
	{root=null;}
	
	
	void addPositionForWord(String str, Position p)/*: Add position p to the word-entry of str. If a word entry for str is already
present in the page index, then add p to the word entry. 
Otherwise, create a new word-entry for str with just one position entry p.*/
	{
		
		ListIterator<WordEntry> it=wrdentries.listIterator();
		WordEntry r=null;
		while(it.hasNext())
		{
			r=it.next();	
			if(str.equals(r.word))
			{
				r.entries.add(p);  // word should be present only once
				break;
			}
		}
		
		r=new WordEntry(str);
		r.addPosition(p);
		wrdentries.add(r);
		
		
		   avlnode u=new avlnode(p,str);	  // inserting in avl tree//TODO
		   root=avlinsert(root,u);
	}

	
	LinkedList<WordEntry> getWordEntries()// Return a list of all word entries stored in the page index.
	{return wrdentries;}
	
	WordEntry getWordEntry(String word)// Return word entry of word
	{
		ListIterator<WordEntry> it=wrdentries.listIterator();
		WordEntry r=null;
		while(it.hasNext())
		{
			r=it.next();
			if(r.word.equals(word))
			{return r;}
		}
		
		return r; 
	}
	
	  class avlnode
	  {
		  Position position;
		  String word;
		  int height=0,key;
		  avlnode left,right,parent;
		  
		  avlnode(Position o,String wrd)
		  {position=o;word=wrd;
		  key=position.wordindex;		  
		  height=0;
		  left=null;right=null;parent=null;}
	  }
	  
	  
	  
	  int gtbl(avlnode u) 
	  {
	      if (u == null) 
	      {return 0;}
	      
	      int lh=0,rh=0;//TODO
	      if(u.left!=null)
	      {lh=u.left.height;}
	      if(u.right!=null)
	      {rh=u.right.height;}
	      
	      return lh - rh;
	  }
	  
	  avlnode rightRotate(avlnode y) 
	  {
		  avlnode x = y.left;
		  avlnode T2 = x.right;

	      x.right = y;
	      y.left = T2;

	      y.height = Math.max(y.left==null?0:y.left.height,y.right==null?0:y.right.height) + 1;
	      x.height = Math.max(x.left==null?0:x.left.height,x.right==null?0:x.right.height) + 1;

	      return x;
	  }

	  avlnode leftRotate(avlnode x) 
	  {
		  avlnode y = x.right;
		  avlnode T2 = y.left;

	      y.left = x;
	      x.right = T2;

	      x.height = Math.max(x.left==null?0:x.left.height,x.right==null?0:x.right.height) + 1;
	      y.height = Math.max(y.left==null?0:y.left.height,y.right==null?0:y.right.height) + 1;

	      return y;
	  }

	  
	  avlnode avlinsert(avlnode node,avlnode pos)
	  {
	
		  if(node==null)
		  {return pos;}
		  
		  if (pos.key < node.key) {
	          node.left = avlinsert(node.left,pos);
	      } else {
	          node.right = avlinsert(node.right,pos);
	      }
		  
		  
		  int lh=0,rh=0;
		  if(node.left!=null)
		  {lh=node.left.height;}
		  if(node.right!=null)
		  {rh=node.right.height;}
		  
		  node.height = Math.max(lh,rh) + 1;
		  
		  
		  int balance = gtbl(node);
		  
		  
		  if (balance > 1 && pos.key < node.left.key) 
		  {return rightRotate(node);}

	      if (balance < -1 && pos.key > node.right.key) 
	      {return leftRotate(node);}

	      if (balance > 1 && pos.key > node.left.key) 
	      {
	          node.left = leftRotate(node.left);
	          return rightRotate(node);
	      }

	      if (balance < -1 && pos.key < node.right.key) 
	      {
	          node.right = rightRotate(node.right);
	          return leftRotate(node);
	      }
	      
	      
		 return node;
		  
	  }
	  
	  avlnode find(avlnode node,int key)
	  {
		  avlnode temp=null;
		  
		  if(node==null)
		  {temp=null;return temp;}
		  
		  if(node.key==key)
		  {temp=node;}
		  else if(key<node.key)
		  {temp=find(node.left,key);}
		  else if(key>node.key)
		  {temp=find(node.right,key);}
		  
		  return temp;
	  }
	  
	  avlnode successor(avlnode node) //returns successor of a particular node
	  {
		  avlnode temp=null;
		  if(node.right!=null)
		  {
			  temp=node.right;
			  while(temp.left!=null)
			  {
				  temp=temp.left;
			  }
			  
		  }
		  else if(node.right==null) //find the last left turn from the root to this node
		  {
			  temp=root;
			  int key=node.position.wordindex;
			  avlnode lastleft=null;
			  
			  while(temp!=null)
			  {
				  if(temp.key<key)
				  {temp=temp.right;}
				  else if(key<temp.key)
				  {lastleft=temp;  //!! write it before
				  temp=temp.left;}
				  else
				  {break;}
			  }
			  
			  
			  temp=lastleft;
			}
		  
		  return temp; 
	  }
	 
}



class PageEntry
{
	
	PageIndex pageindex;
	String pagename;
	PageEntry(String pageName)//The argument is the name of the document. Read this file, and create the page index.
	{
		pagename=pageName;
		
		pageindex=new PageIndex();
		int index=0;
		
		try {
			 FileInputStream fstream = new FileInputStream ("webpages//"+pageName);
			 Scanner s = new Scanner ( fstream );
			 
			 while (s. hasNextLine ())
			 {
				 String str=s.nextLine();
				 str=filter(str);
				 Scanner scn=new Scanner (str);
					
				 while(scn.hasNext())
				 {++index;
				 
					 String r=scn.next();
					 r=r.toLowerCase();
					
					 if(r.equals("a") || r.equals("an") || r.equals("the"))
					 {continue;}
					 else if(r.equals("they") || r.equals("these") || r.equals("this"))
					 {continue;}
					 else if(r.equals("for") || r.equals("is") || r.equals("are"))
					 {continue;}
					 else if(r.equals("was") || r.equals("of") || r.equals("or"))
					 {continue;}
					 else if(r.equals("and") || r.equals("does") || r.equals("will"))
					 {continue;}
					 else if(r.equals("whose"))
					 {continue;}
					 else if(r.equals("stacks"))
					 {r="stack";}
					 else if(r.equals("structures"))
					 {r="structure";}
					 else if(r.equals("applications"))
					 {r="application";}
					 
				
					 Position p=new Position(this,index);
					 pageindex.addPositionForWord(r, p);
				//	 System . out . println (pagename+" "+r);
					 
				 }
				 
				 				 
			 }
			 System.out.println("Page "+pagename+" added");
		} 
		catch ( FileNotFoundException e) 
		     {System . out . println ("Exception- File "+pagename+" not found in webpages folder");}
	}
	
	
	String filter(String s)
	{
		int n=s.length();
		char[] a=s.toCharArray();
		for(int i=0;i<n;++i)
		{
			if(a[i]=='{' || a[i]=='}')
			{a[i]=' ';}
			else if(a[i]=='[' || a[i]==']')
			{a[i]=' ';}
			else if(a[i]=='<' || a[i]=='>')
			{a[i]=' ';}
			else if(a[i]=='=')
			{a[i]=' ';}
			else if(a[i]=='(' || a[i]==')')
			{a[i]=' ';}
			else if(a[i]=='.' || a[i]==',')
			{a[i]=' ';}
			else if(a[i]==';' || a[i]==39)  // a[i]=='''  //  ASCII value of ' is 39
			{a[i]=' ';}
			else if(a[i]=='"' || a[i]=='?')
			{a[i]=' ';}
			else if(a[i]=='#' || a[i]=='!')
			{a[i]=' ';}
			else if(a[i]=='-' || a[i]==':')
			{a[i]=' ';}
		}
		
		s= String.valueOf(a);
		s=s.toLowerCase();
		return s;
	}
	
	PageIndex getPageIndex()
	{return pageindex;}
	
	
	double getRelevanceOfPage(LinkedList <String> str, int flag)
	// Return the relevance of the webpage for a group of words represented by the array str[ ]
	{
		double relevance=0,rel=0;
		
		
		if(flag==1)   //AND
		{
			
	    	ListIterator<String> it=str.listIterator(); 
		    	
	    	while(it.hasNext())
	    	{
	    		String wordtofind=it.next();//check if this word matches with any word entry of the page
	    		
	    		ListIterator<WordEntry> it2=pageindex.wrdentries.listIterator(); 
			    int found=0;
	    		while(it2.hasNext())
	    		{
	    			WordEntry wrd=it2.next();
	    			if(wordtofind.equals(wrd.word))
	    			{
	    				rel=0;
	    				
	    				ListIterator<Position> it3=wrd.entries.listIterator(); 
	    				while(it3.hasNext())
	    				{
	    					double temp=it3.next().wordindex;
	    					
	    					temp=temp*temp;
	    					rel+=1.0/temp;
	    					
	    				}
	    				
	    				found=1;
	    				break;//
	    			}
	    		}
	    		if(found==0) //word not found in page
	    		{return 0;} // return from function because it is and query 
	    		else if(found==1)
	    		{
	    			relevance+=rel;
	    		}
	    	
	    	}
		}
		else if(flag==2)	//OR
		{
	    	ListIterator<String> it=str.listIterator(); 
	    	
	    	while(it.hasNext())
	    	{
	    		String wordtofind=it.next();//check if this word matches with any word entry of the page
	    		
	    		ListIterator<WordEntry> it2=pageindex.wrdentries.listIterator(); 
			    int found=0;
	    		while(it2.hasNext())
	    		{
	    			WordEntry wrd=it2.next();
	    			if(wordtofind.equals(wrd.word))
	    			{
	    				rel=0;
	    				
	    				ListIterator<Position> it3=wrd.entries.listIterator(); 
	    				while(it3.hasNext())
	    				{
	    					double temp=it3.next().wordindex;
	    					
	    					temp=temp*temp;
	    					rel+=1.0/temp;
	    					
	    				}
	    				
	    				found=1;
	    				break;//
	    			}
	    		}
	    		if(found==0) //word not found in page
	    		{} // do nothing because it is 'or' query 
	    		else if(found==1)
	    		{
	    			relevance+=rel;
	    		}
	    	//if relevance is non zero then only some word was found somewhere
	    	}
		}
		else if(flag==3)	//phrase
		{
			relevance=0;
			
			ListIterator<String> it=str.listIterator(); 
			
			String firstword=it.next();


			int noofwords=str.size();
			//System.out.println("*firstword= "+pageindex.getWordEntry(firstword));
			
			WordEntry temp2=pageindex.getWordEntry(firstword);
			if(temp2==null)
			{return 0;}
			LinkedList <Position> positions=temp2.entries;
		
			
			//	System.out.println("*positions= "+positions);
			ListIterator<Position> it2=positions.listIterator(); 
			
			while(it2.hasNext())
			{
				int idx=it2.next().wordindex;
				PageIndex.avlnode start=pageindex.find(pageindex.root,idx);
				
		//		System.out.println("*start= "+start);
			
			PageIndex.avlnode curr;
			curr=start;
			
			if(start==null)
			{relevance=0;return relevance;}
			
			it=str.listIterator(); 
			int e=0;
			while(it.hasNext())
		    {
		    	String wordtofind=it.next();//check if this word matches with any word entry of the page
		    	
		    	if(curr==null)
		    	{e=0;break;}
		    	
		    	if(wordtofind.equals(curr.word)==false)
		    	{
		    		e=0;	
		    		break;
		    	}
		    	else
		    	{++e;}
	    		
		  	curr=pageindex.successor(curr);
		    }
		    
			if(e==noofwords)
			{double u=idx;u=u*u;relevance+=1.0/u;}
			
			}
			
		}
		
	//	if(flag==3 && relevance>0)
	//	System.out.println(pagename+" *relevance= "+relevance);
		
		return relevance;
	}
	
}


class MySort
{
	ArrayList<SearchResult> sortThisList(ArrayList<SearchResult> listOfSortableEntries) //TODO//60 done
	{
		
		int n=listOfSortableEntries.size();
		
		
		for(int i=0;i<n;++i)
		{
			for(int j=i+1;j<n;++j)
			{
				if(listOfSortableEntries.get(i).relevance<listOfSortableEntries.get(j).relevance)
				{
					SearchResult a=listOfSortableEntries.get(i),b=listOfSortableEntries.get(j);
					
					listOfSortableEntries.set(i,b);
					listOfSortableEntries.set(j,a);
				}	
			}
		}
		
		
		//sorted in decreasing order from 0 to n-1
		return listOfSortableEntries;
	}
}


