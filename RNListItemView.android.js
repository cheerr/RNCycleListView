/**
 * Created by chenwei on 2017/8/7.
 */

import React, { Component } from 'react'
import {
  requireNativeComponent,
} from 'react-native'

const ReactNative = require('ReactNative')
const UIManager = require('UIManager')

const RNListItemView = requireNativeComponent('RNListItemView', null)
const ITEM_VIEW_KEY = 'RNListItemViewKey'

class Index extends Component {

  static getDefaultProps () {

    return {
      rowHeight: 0,
      renderRow: undefined,  //(dataObject,rowSizeObject)=>{}
      position: 0,
    }

  }

  constructor (props) {
    super(props)
    this.state = {
      position: this.props.position ? this.props.position : 0
    }
  }

  onRNListViewUpdateView (event) {

    const {position} = event.nativeEvent

    console.log('RNList onRNListViewUpdateView', this.state.position, 'position', position)

    if (this.state.position !== position) {
      this.setState({position: position})
    }
  }

  componentDidMount () {
  }

  render () {
    return <RNListItemView
      {...this.props}
      ref={ITEM_VIEW_KEY}
      onRNListViewUpdateView={(v) => {
        this.onRNListViewUpdateView(v)
      }}>
      {
        typeof this.props.renderRow === 'function' && this.props.renderRow(this.state.position)
      }
    </RNListItemView>
  }

}

export default Index
