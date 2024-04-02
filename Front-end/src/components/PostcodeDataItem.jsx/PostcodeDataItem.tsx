import styles from "./PostcodeDataItem.module.scss";

interface PostcodeDataItemProps {
  postcode: Number;
  suburb: string;
}

const PostcodeDataItem: React.FC<PostcodeDataItemProps> = ({
  postcode,
  suburb,
}) => {
  return (
    <article className={styles.postcodeDataItem}>
      <h3>{`${suburb}, ${postcode}`}</h3>
    </article>
  );
};

export default PostcodeDataItem;
