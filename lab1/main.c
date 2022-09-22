#include <stdio.h>
#include <stdlib.h>
#define MAX 7

void insert();
void delete();
void display();
void load_file();
void save_file();
void search();
void sort_queue();
void reverse();
int priority_queue();
int circular_queue();
void cq_insert();
void cq_delete();
void cq_display();
void enqueue_pr();
void dequeue_pr();
void display_pr();
int peek();

int queue_array[MAX];
int rear = - 1;
int front = - 1;

int pqVal[MAX];
int pqPriority[MAX];
int idx = -1;

int main(){
    int choice;
    //menu
    while (1){
        printf("\n");
#define MAX 7
        printf("------------------MAIN MENU--------------------------------\n");
        printf("-----------------------------------------------------------\n");
        printf("1.Insert element to queue (queue) \n");
        printf("2.Delete element from queue (dequeue) \n");
        printf("3.Display all elements of queue \n");
        printf("4.Open file \n");
        printf("5.Save to a file \n");
        printf("6.Search in queue \n");
        printf("7.Sort elements \n");
        printf("8.Reverse queue \n");
        printf("9.Enter the priority queue menu \n");
        printf("10.Enter the circular queue menu \n");
        printf("0.Quit \n");
        printf("Enter your choice : ");

        scanf("%d", &choice);

        switch(choice){
        case 1: insert(); break;
        case 2: delete(); break;
        case 3: display(); break;
        case 4: load_file(); break;
        case 5: save_file(); break;
        case 6: search(); break;
        case 7: sort_queue(); break;
        case 8: reverse(); break;
        case 9: priority_queue(); break;
        case 10: circular_queue(); break;
        case 0: exit(1);
        default: printf("Wrong choice \n");
        }
        printf("\n");
    }
}
void insert(){
    int item;
    if(rear == MAX - 1)
        printf("Queue Overflow \n");
    else{
        if(front== - 1 || rear)
            front = 0;
            printf("Insert the element in queue : ");
            scanf("%d", &item);
            rear = rear + 1;
            queue_array[rear] = item;
    }
}
void delete(){
    if(front == - 1 || front > rear){
        printf("Queue Underflow \n");
        return;
    }
    else{
        printf("Element deleted from queue is : %d \n", queue_array[front]);
        front = front + 1;
    }
}
void display(){
    int i;

    if(front == - 1)
        printf("Queue is empty \n");
    else{
        printf("Queue is : \n");
        for(i = front; i <= rear; i++)
            printf("%d ", queue_array[i]);
            printf("\n");
    }
}
void load_file(){
    int i = 0;
    front = 0;
    rear = -1;

    FILE *in_file  = fopen("in_file.txt", "r");

    if (in_file == NULL) {   
        printf("Error! Could not open file\n"); 
        exit(-1);
    }
    else{
        printf("File uploaded successfully \n");
        while(fscanf(in_file, "%d ", &queue_array[i]) != EOF){
            i++;
            rear++;
        }
    }
    fclose(in_file);
}
void save_file() {
    int i;
    FILE *out_file  = fopen("out_file.txt", "w");
    freopen(NULL, "w+", out_file);

    if(front == - 1)
        printf("Queue is empty \n");
    else{
        for (i = front; i != rear; i = (i + 1) % MAX)
            fprintf(out_file, "%d ", queue_array[i]);
        fprintf(out_file, "%d ", queue_array[i]);
    }

    fclose(out_file);
}
void search() {
    int s_el;
    int found = 0;
    printf("Search for: ");
    scanf("%d", &s_el);

    if(front == - 1)
        printf("Queue is empty \n");
    else{
        for (int i = 0; i <= rear; i++) {
            if (queue_array[i] == s_el) 
                found = 1;
        }
        if (found == 1) {
            printf("Element has been found \n");
        }
        else 
            printf("Element has not been found \n");
    }
}
void sort_queue(){
    int temp;

    if(front == - 1)
        printf("Queue is empty \n");
    else{
        for (int i = front; i <= rear; ++i) {
            for (int j = i + 1; j <= rear; ++j) {
                if (queue_array[i] > queue_array[j]) {
                    temp = queue_array[i];
                    queue_array[i] = queue_array[j];
                    queue_array[j] = temp;
                }
            }
        }
    }  
}
void reverse() {
    int temp;
    int i, j;
    if(front == - 1)
        printf("Queue is empty \n");
    else {
        for(i = front, j = rear; i < j; i++, j--){
            temp = queue_array[i];
            queue_array[i] = queue_array[j];
            queue_array[j] = temp;
	    }   
        
        printf("The queue has been reversed\n");
    }
}
 int priority_queue(){
   int choice;

    while (1){
        printf("\n");
       printf("---------PRIORITY QUEUE MENU-------------------\n");
        printf("-----------------------------------------------\n");
        printf("1.Insert element to priority queue (queue) \n");
        printf("2.Delete element from priority queue (dequeue) \n");
        printf("3.Display all elements \n");
        printf("4.Show the element with the highest priority \n");
        printf("0.Go to main menu \n");
        printf("Enter your choice, please : ");

        scanf("%d", &choice);

       switch(choice){
        case 1: enqueue_pr(); break;
        case 2: dequeue_pr(); break;
        case 3: display_pr(); break;
        case 4: printf("Element with the highest priority is "); peek(); break;
        case 0: main();
        default: printf("Wrong choice. Try one of the options available. \n");
        }
        printf("\n");
    }
}
// Insert the element in maintaining items in sorted order of their priority
void enqueue_pr()
{
    if(!(idx == MAX - 1)){
        int data, priority;
        int tempVal, tempPr, i, j;
        printf("Insert the element in priority queue (nr and priority separated by space): ");
        scanf("%d %d", &data, &priority);
        // first item being entered
        if(idx == -1){
            idx++; // increase the index
            pqVal[idx] = data;
            pqPriority[idx] = priority;
            return;
        }
        else{
             // Increase the index
            idx++;
            pqVal[idx] = data;
            pqPriority[idx] = priority;

            for(i = 0; i <= idx; i++)    
            {    
                for(j = i+1; j <= idx; j++)    
                    {    
                        if(pqPriority[j] < pqPriority[i])    
                        {    
                            tempPr = pqPriority[i];
                            tempVal = pqVal[i];   

                            pqPriority[i] = pqPriority[j];  
                            pqVal[i] = pqVal[j];  

                            pqPriority[j] = tempPr;     
                            pqVal[j] = tempVal;
                        }     
                    }     
            }     
            
        }

    }
}
void dequeue_pr()
{
        printf("The deleted element :");
        peek();
        idx--;
}
void display_pr(){
    if (idx == -1)
        {printf("Queue is empty.");}
    for (int i = 0; i <= idx; i++) {
        printf("(%d, %d)\n",pqVal[i], pqPriority[i]);
    } 
}
int peek()
{
    printf("(%d, %d)\n",pqVal[idx], pqPriority[idx]);
}

int circular_queue(){
    int choice;

    while (1){
        printf("\n");
        printf("------------CIRCULAR QUEUE MENU----------------------------\n");
        printf("-----------------------------------------------------------\n");
        printf("1.Insert element to circular queue (queue) \n");
        printf("2.Delete element from circular queue (dequeue) \n");
        printf("3.Display all elements \n");
        printf("0.Return to main menu \n");
        printf("Enter your choice : ");

        scanf("%d", &choice);

        switch(choice){
        case 1: cq_insert(); break;
        case 2: cq_delete(); break;
        case 3: cq_display(); break;
        case 0: main();
        default: printf("Wrong choice \n");
        }
        printf("\n");
    }
}
void cq_insert(){
    int item;
    if ((front == rear + 1) || (front == 0 && rear == MAX - 1))
        printf("Queue is full! \n");
    else {
        if (front == -1) 
            front = 0;
            printf("Inset the element in queue : ");
            scanf("%d", &item);
            rear = (rear + 1) % MAX;
            queue_array[rear] = item;
    }
}
void cq_delete(){
    if (front == -1) {
        printf("Queue is empty! \n");
    } 
    else {
        if (front == rear) {
            front = -1;
            rear = -1;
        } 
        else {
            printf("Element deleted from queue is : %d \n", queue_array[front]);
            front = (front + 1) % MAX;
        }
    }
}
void cq_display(){
    int i;
    if (front == -1)
        printf("Empty Queue\n");
    else {
        printf("Queue is : \n");
        for (i = front; i != rear; i = (i + 1) % MAX) {
            printf("%d ", queue_array[i]);
        }
        printf("%d ", queue_array[i]);
    }
}