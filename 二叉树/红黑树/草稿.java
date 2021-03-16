//package 二叉树.红黑树;
//
//
//public void addAfter(Red_Node node){
//        Red_Node parent = node.parent;
//        if(parent == null){
//        black(node);
//        }
//        //四种父节点为黑色的情况处理
//        if(isBlack(parent))return;
//        Red_Node uncle = parent.sibling();
//        Red_Node grand = parent.parent;
//        if(!isRed(uncle)){
//        if(parent.isParentLeft_Child()){
//        if(node.isParentLeft_Child()){   //LL
//        red(grand);
//        black(parent);
//        Run_Right(grand);
//        }else {                              //LR
//        black(node);
//        red(grand);
//        Run_Left(parent);
//        Run_Right(grand);
//        }
//        }
//        else {
//        if(node.isParentRight_Child()){      //RR
//        black(parent);
//        red(grand);
//        Run_Left(grand);
//        }else {                                  //RL
//        black(node);
//        red(grand);
//        Run_Right(parent);
//        Run_Left(grand);
//        }
//        }
//        }
//        else {
//        //双红情况
//        black(parent);
//        black(uncle);
//        addAfter(red(grand));
//        }
//        }
//
////左旋转
//public void Run_Left(Red_Node grand){
//        Red_Node parent = grand.right;
//        Red_Node child = parent.left;
//        grand.right = child;
//        parent.left = grand;
//
//        parent.parent=grand.parent;
//        if(grand.isParentLeft_Child()){
//        grand.parent.left = parent;
//        }
//        else if(grand.isParentRight_Child()){
//        grand.parent.right = parent;
//
//        }else {
//        root = parent;
//        }
//        if(child != null){
//        child.parent = grand;
//        }
//        grand.parent = parent;
//        }
////右旋转
//public void Run_Right(Red_Node grand){
//        Red_Node parent = grand.left;
//        Red_Node child = parent.right;
//        grand.left = child;
//        parent.right = grand;
//
//        parent.parent=grand.parent;
//        if(grand.isParentLeft_Child()){
//        grand.parent.left = parent;
//        }
//        else if(grand.isParentRight_Child()){
//        grand.parent.right = parent;
//
//        }else {
//        root = parent;
//        }
//        if(child != null){
//        child.parent = grand;
//        }
//        grand.parent = parent;
//        }
///**
// * 分界线
// * @param node
// */